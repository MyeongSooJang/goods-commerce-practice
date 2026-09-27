package com.goods.payment.application.service;

import com.goods.payment.application.dto.EscrowReleaseCommand;
import com.goods.payment.application.dto.EscrowReleaseResult;
import com.goods.payment.application.event.SettlementCandidateCreatedEvent;
import com.goods.payment.application.usecase.EscrowReleaseUseCase;
import com.goods.payment.common.exception.EscrowNotFoundException;
import com.goods.payment.common.exception.InvalidOrderPaymentRequestException;
import com.goods.payment.domain.entity.Escrow;
import com.goods.payment.domain.entity.EscrowTransaction;
import com.goods.payment.domain.enumtype.EscrowStatus;
import com.goods.payment.domain.repository.EscrowRepository;
import com.goods.payment.domain.repository.EscrowTransactionRepository;
import com.goods.payment.domain.service.IdentifierGenerator;
import com.goods.payment.domain.service.TimeProvider;
import com.goods.payment.infrastructure.messaging.kafka.SettlementCandidateCreatedOutboxEventSaver;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
/**
 * escrow 해제 유스케이스를 담당한다.
 * 구매확정 이후 seller별 escrow들을 RELEASED로 전환하고 정산 후보 이벤트를 발행한다.
 */
public class EscrowReleaseService implements EscrowReleaseUseCase {

    private final EscrowRepository escrowRepository;
    private final EscrowTransactionRepository escrowTransactionRepository;
    private final IdentifierGenerator identifierGenerator;
    private final SettlementCandidateCreatedOutboxEventSaver settlementCandidateCreatedOutboxEventSaver;
    private final TimeProvider timeProvider;

    public EscrowReleaseService(
            EscrowRepository escrowRepository,
            EscrowTransactionRepository escrowTransactionRepository,
            IdentifierGenerator identifierGenerator,
            SettlementCandidateCreatedOutboxEventSaver settlementCandidateCreatedOutboxEventSaver,
            TimeProvider timeProvider
    ) {
        this.escrowRepository = escrowRepository;
        this.escrowTransactionRepository = escrowTransactionRepository;
        this.identifierGenerator = identifierGenerator;
        this.settlementCandidateCreatedOutboxEventSaver = settlementCandidateCreatedOutboxEventSaver;
        this.timeProvider = timeProvider;
    }

    @Override
    /**
     * orderId와 sellerMemberId에 해당하는 escrow들을 해제하고 후속 이벤트를 발행한다.
     * 이미 RELEASED 상태면 기존 결과를 그대로 반환해 멱등하게 처리한다.
     */
    public EscrowReleaseResult releaseEscrow(EscrowReleaseCommand command) {
        validateCommand(command);

        // orderItem 단위 escrow로 전환되어도 구매확정 단위는 orderId + sellerMemberId를 유지한다.
        List<Escrow> sellerEscrows = escrowRepository.findAllByOrderIdAndSellerMemberId(
                command.orderId(),
                command.sellerMemberId()
        );
        if (sellerEscrows.isEmpty()) {
            throw new EscrowNotFoundException();
        }

        LocalDateTime now = timeProvider.now();
        java.math.BigDecimal releasedAmount = java.math.BigDecimal.ZERO;
        boolean releasedAny = false;

        for (Escrow escrow : sellerEscrows) {
            if (escrow.isReleased()) {
                releasedAmount = releasedAmount.add(escrow.getAmount());
                continue;
            }
            if (escrow.isRefunded()) {
                continue;
            }
            if (!escrow.isHeld()) {
                throw new IllegalStateException("Escrow is not releasable.");
            }

            java.math.BigDecimal beforeAmount = escrow.getAmount();
            escrow.release(now, now);
            escrowRepository.save(escrow);
            recordReleaseEscrowTransaction(escrow, beforeAmount, escrow.getAmount(), now);
            releasedAny = true;
            releasedAmount = releasedAmount.add(escrow.getAmount());

            // settlement는 escrow 단위 원천 항목을 적재하므로, 해제된 escrow별로 후보를 발행한다.
            settlementCandidateCreatedOutboxEventSaver.save(new SettlementCandidateCreatedEvent(
                    identifierGenerator.generateUuid(),
                    escrow.getOrderId(),
                    escrow.getEscrowId(),
                    escrow.getSellerMemberId(),
                    escrow.getAmount(),
                    escrow.getReleasedAt(),
                    command.confirmationType(),
                    now
            ));
        }

        if (!releasedAny) {
            return existingResult(command.orderId(), releasedAmount);
        }

        return new EscrowReleaseResult(
                command.orderId(),
                releasedAmount,
                releasedAmount.compareTo(java.math.BigDecimal.ZERO) > 0 ? EscrowStatus.RELEASED : EscrowStatus.REFUNDED,
                now
        );
    }

    /**
     * 이미 해제된 escrow의 결과를 현재 응답 형식으로 재구성한다.
     */
    private EscrowReleaseResult existingResult(UUID orderId, java.math.BigDecimal releasedAmount) {
        return new EscrowReleaseResult(
                orderId,
                releasedAmount,
                releasedAmount.compareTo(java.math.BigDecimal.ZERO) > 0 ? EscrowStatus.RELEASED : EscrowStatus.REFUNDED,
                timeProvider.now()
        );
    }

    /**
     * escrow 해제 계약의 최소 필수 입력만 검증한다.
     */
    private void validateCommand(EscrowReleaseCommand command) {
        if (command == null) {
            throw new InvalidOrderPaymentRequestException("에스크로 정산 요청은 필수입니다.");
        }
        if (command.orderId() == null) {
            throw new InvalidOrderPaymentRequestException("주문 ID는 필수입니다.");
        }
        if (command.sellerMemberId() == null) {
            throw new InvalidOrderPaymentRequestException("판매자 회원 ID는 필수입니다.");
        }
        if (command.confirmationType() == null) {
            throw new InvalidOrderPaymentRequestException("구매 확정 유형은 필수입니다.");
        }
    }

    private void recordReleaseEscrowTransaction(
            Escrow escrow,
            java.math.BigDecimal beforeAmount,
            java.math.BigDecimal afterAmount,
            LocalDateTime occurredAt
    ) {
        EscrowTransaction transaction = EscrowTransaction.release(
                identifierGenerator.generateUuid(),
                escrow.getEscrowId(),
                escrow.getOrderId(),
                escrow.isOrderItemReference() ? escrow.getReferenceId() : null,
                escrow.getSellerMemberId(),
                escrow.getBuyerMemberId(),
                beforeAmount,
                beforeAmount,
                afterAmount,
                null,
                "ESCROW_RELEASE",
                "escrow release",
                occurredAt,
                occurredAt
        );
        escrowTransactionRepository.save(transaction);
    }
}
