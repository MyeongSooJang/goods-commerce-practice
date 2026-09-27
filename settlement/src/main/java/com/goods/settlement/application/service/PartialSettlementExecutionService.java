package com.goods.settlement.application.service;

import com.goods.settlement.application.dto.PartialSettlementCreateCommand;
import com.goods.settlement.application.dto.PartialSettlementCreateResult;
import com.goods.settlement.application.dto.PartialSettlementExecutionCommand;
import com.goods.settlement.application.dto.PartialSettlementExecutionResult;
import com.goods.settlement.application.usecase.PartialSettlementCreationUseCase;
import com.goods.settlement.application.usecase.PartialSettlementExecutionUseCase;
import com.goods.settlement.application.usecase.SettlementPayoutUseCase;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 판매자 부분 정산 생성과 지급 요청 발행을 함께 처리하는 서비스다.
 */
@Service
@Transactional
public class PartialSettlementExecutionService implements PartialSettlementExecutionUseCase {

    private final PartialSettlementCreationUseCase partialSettlementCreationUseCase;
    private final SettlementPayoutUseCase settlementPayoutUseCase;

    public PartialSettlementExecutionService(
            PartialSettlementCreationUseCase partialSettlementCreationUseCase,
            SettlementPayoutUseCase settlementPayoutUseCase
    ) {
        this.partialSettlementCreationUseCase = partialSettlementCreationUseCase;
        this.settlementPayoutUseCase = settlementPayoutUseCase;
    }

    @Override
    public PartialSettlementExecutionResult executePartialSettlement(PartialSettlementExecutionCommand command) {
        Objects.requireNonNull(command, "command must not be null.");

        PartialSettlementCreateResult partialSettlementCreateResult = partialSettlementCreationUseCase.createPartialSettlement(
                new PartialSettlementCreateCommand(
                        command.sellerId(),
                        command.settlementItemIds()
                )
        );
        settlementPayoutUseCase.requestPayoutForPartialSettlement(partialSettlementCreateResult.settlementId());

        return new PartialSettlementExecutionResult(
                partialSettlementCreateResult.settlementId(),
                partialSettlementCreateResult.sellerId(),
                partialSettlementCreateResult.settlementType(),
                partialSettlementCreateResult.settlementStatus(),
                partialSettlementCreateResult.settlementItemCount(),
                partialSettlementCreateResult.totalSalesAmount(),
                partialSettlementCreateResult.feeAmount(),
                partialSettlementCreateResult.finalSettlementAmount()
        );
    }
}
