package com.example.notification.infrastructure.messaging.kafka.handler;

import com.example.notification.application.usecase.NotificationUsecase;
import com.example.notification.infrastructure.messaging.kafka.contract.PayoutFailureReason;
import com.example.notification.infrastructure.messaging.kafka.contract.SellerSettlementPayoutResultMessage;
import com.example.notification.infrastructure.messaging.kafka.contract.SellerSettlementPayoutResultStatus;
import com.example.notification.infrastructure.messaging.kafka.dlq.exception.InvalidEventPayloadException;
import com.example.common.event.contract.EventEnvelope;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class SellerSettlementPayoutResultNotificationEventHandler implements NotificationEventHandler {

    private static final ZoneId KOREA_ZONE_ID = ZoneId.of("Asia/Seoul");
    private static final String SELLER_SETTLEMENT_PAYOUT_RESULT_EVENT_TYPE = "SELLER_SETTLEMENT_PAYOUT_RESULT";

    private final NotificationUsecase notificationUsecase;
    private final ObjectMapper objectMapper;

    @Override
    public String supportsEventType() {
        return SELLER_SETTLEMENT_PAYOUT_RESULT_EVENT_TYPE;
    }

    @Override
    public void handle(EventEnvelope<JsonNode> event) {
        EventEnvelope<SellerSettlementPayoutResultMessage> typedEvent = new EventEnvelope<>(
                event.eventId(),
                event.eventType(),
                event.source(),
                event.aggregateId(),
                event.recipientId(),
                event.occurredAt(),
                event.traceId(),
                objectMapper.convertValue(event.payload(), SellerSettlementPayoutResultMessage.class)
        );

        validateSellerSettlementPayoutResultEvent(typedEvent);

        LocalDateTime processedAt = toKoreaLocalDateTime(typedEvent.occurredAt());
        SellerSettlementPayoutResultMessage payload = typedEvent.payload();
        if (payload.resultStatus() == SellerSettlementPayoutResultStatus.SUCCESS) {
            notificationUsecase.createSellerSettlementPayoutSucceededNotification(
                    typedEvent.eventId(),
                    typedEvent.traceId(),
                    payload.settlementId(),
                    payload.sellerMemberId(),
                    payload.payoutAmount(),
                    processedAt
            );
            return;
        }

        notificationUsecase.createSellerSettlementPayoutFailedNotification(
                typedEvent.eventId(),
                typedEvent.traceId(),
                payload.settlementId(),
                payload.sellerMemberId(),
                payload.failureReason(),
                processedAt
        );
    }

    private void validateSellerSettlementPayoutResultEvent(EventEnvelope<SellerSettlementPayoutResultMessage> event) {
        if (event == null) {
            throw new InvalidEventPayloadException("판매자 정산 지급 결과 이벤트는 필수입니다.");
        }
        if (!SELLER_SETTLEMENT_PAYOUT_RESULT_EVENT_TYPE.equals(event.eventType())) {
            throw new InvalidEventPayloadException("지원하지 않는 eventType입니다: " + event.eventType());
        }
        if (event.recipientId() == null) {
            throw new InvalidEventPayloadException("recipientId는 필수입니다.");
        }
        if (event.payload() == null) {
            throw new InvalidEventPayloadException("payload는 필수입니다.");
        }
        if (event.payload().settlementId() == null) {
            throw new InvalidEventPayloadException("payload.settlementId는 필수입니다.");
        }
        if (event.payload().sellerMemberId() == null) {
            throw new InvalidEventPayloadException("payload.sellerMemberId는 필수입니다.");
        }
        if (event.payload().resultStatus() == null) {
            throw new InvalidEventPayloadException("payload.resultStatus는 필수입니다.");
        }
        if (event.payload().resultStatus() == SellerSettlementPayoutResultStatus.SUCCESS
                && (event.payload().payoutAmount() == null || event.payload().payoutAmount() <= 0)) {
            throw new InvalidEventPayloadException("정산 지급 성공 이벤트에는 양수인 payload.payoutAmount가 필요합니다.");
        }
        if (event.payload().resultStatus() == SellerSettlementPayoutResultStatus.FAILED
                && event.payload().failureReason() == null) {
            throw new InvalidEventPayloadException("정산 지급 실패 이벤트에는 payload.failureReason이 필수입니다.");
        }
        if (!Objects.equals(event.recipientId(), event.payload().sellerMemberId())) {
            throw new InvalidEventPayloadException("recipientId와 payload.sellerMemberId가 일치해야 합니다.");
        }
    }

    private LocalDateTime toKoreaLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, KOREA_ZONE_ID);
    }
}
