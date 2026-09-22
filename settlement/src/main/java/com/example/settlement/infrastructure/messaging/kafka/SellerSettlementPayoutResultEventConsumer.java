package com.example.settlement.infrastructure.messaging.kafka;

import com.example.settlement.application.usecase.SettlementPayoutUseCase;
import com.example.settlement.infrastructure.messaging.kafka.contract.SellerSettlementPayoutResultMessage;
import com.example.settlement.infrastructure.messaging.kafka.exception.SettlementKafkaProcessingException;
import com.example.settlement.infrastructure.messaging.kafka.exception.SettlementKafkaValidationException;
import com.example.common.event.contract.EventEnvelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

/**
 * payment -> settlement 정산 지급 결과 이벤트를 소비하는 Kafka consumer(소비기)다.
 */
@Slf4j
@Component
public class SellerSettlementPayoutResultEventConsumer {

    private static final String SELLER_SETTLEMENT_PAYOUT_RESULT_EVENT_TYPE = "SELLER_SETTLEMENT_PAYOUT_RESULT";
    private static final TypeReference<EventEnvelope<SellerSettlementPayoutResultMessage>>
            SELLER_SETTLEMENT_PAYOUT_RESULT_ENVELOPE_TYPE =
            new TypeReference<>() {
            };

    private final SettlementPayoutUseCase settlementPayoutService;
    private final ObjectMapper objectMapper;

    public SellerSettlementPayoutResultEventConsumer(SettlementPayoutUseCase settlementPayoutService, ObjectMapper objectMapper) {
        this.settlementPayoutService = settlementPayoutService;
        this.objectMapper = objectMapper;
    }

    /**
     * 지급 결과 이벤트를 settlement 상태 반영 서비스로 전달한다.
     * transport 계층에서는 비즈니스 분기 없이 이벤트를 그대로 전달하고,
     * 상태 전이 정책은 application service가 전담한다.
     */
    @KafkaListener(
            topics = KafkaTopics.SETTLEMENT_PAYOUT_RESULT,
            groupId = KafkaConsumerGroups.SETTLEMENT_SERVICE,
            containerFactory = "sellerSettlementPayoutResultKafkaListenerContainerFactory"
    )
    public void listen(String eventJson) {
        try {
            EventEnvelope<SellerSettlementPayoutResultMessage> envelope = readEnvelope(eventJson);
            validateEnvelope(envelope);
            SellerSettlementPayoutResultMessage event = envelope.payload();
            settlementPayoutService.applyPayoutResult(event);
        } catch (SettlementKafkaValidationException exception) {
            log.warn("정산 지급 결과 Kafka 메시지를 DLQ 대상으로 분류합니다. message={}", exception.getMessage(), exception);
            throw exception;
        } catch (Exception exception) {
            log.error("정산 지급 결과 Kafka 메시지 처리 중 재시도 대상 오류가 발생했습니다.", exception);
            throw new SettlementKafkaProcessingException("정산 지급 결과 Kafka 메시지 처리에 실패했습니다.", exception);
        }
    }

    private EventEnvelope<SellerSettlementPayoutResultMessage> readEnvelope(String eventJson) {
        try {
            return objectMapper.readValue(eventJson, SELLER_SETTLEMENT_PAYOUT_RESULT_ENVELOPE_TYPE);
        } catch (Exception exception) {
            throw new SettlementKafkaValidationException("정산 지급 결과 envelope 역직렬화에 실패했습니다.", exception);
        }
    }

    private void validateEnvelope(EventEnvelope<SellerSettlementPayoutResultMessage> envelope) {
        if (envelope == null) {
            throw new SettlementKafkaValidationException("sellerSettlementPayoutResult envelope는 필수입니다.");
        }
        if (envelope.eventId() == null) {
            throw new SettlementKafkaValidationException("eventId는 필수입니다.");
        }
        if (envelope.eventType() == null || envelope.eventType().isBlank()) {
            throw new SettlementKafkaValidationException("eventType은 필수입니다.");
        }
        if (!SELLER_SETTLEMENT_PAYOUT_RESULT_EVENT_TYPE.equals(envelope.eventType())) {
            throw new SettlementKafkaValidationException("eventType이 올바르지 않습니다.");
        }
        if (envelope.source() == null || envelope.source().isBlank()) {
            throw new SettlementKafkaValidationException("source는 필수입니다.");
        }
        if (envelope.aggregateId() == null) {
            throw new SettlementKafkaValidationException("aggregateId는 필수입니다.");
        }
        if (envelope.occurredAt() == null) {
            throw new SettlementKafkaValidationException("occurredAt은 필수입니다.");
        }
        if (envelope.traceId() == null || envelope.traceId().isBlank()) {
            throw new SettlementKafkaValidationException("traceId는 필수입니다.");
        }

        SellerSettlementPayoutResultMessage event = envelope.payload();
        if (event == null) {
            throw new SettlementKafkaValidationException("payload는 필수입니다.");
        }
        if (event.settlementId() == null) {
            throw new SettlementKafkaValidationException("settlementId는 필수입니다.");
        }
        if (event.sellerMemberId() == null) {
            throw new SettlementKafkaValidationException("sellerMemberId는 필수입니다.");
        }
        if (!envelope.aggregateId().equals(event.settlementId())) {
            throw new SettlementKafkaValidationException("aggregateId는 settlementId와 같아야 합니다.");
        }
        if (envelope.recipientId() != null && !envelope.recipientId().equals(event.sellerMemberId())) {
            throw new SettlementKafkaValidationException("recipientId는 sellerMemberId와 같아야 합니다.");
        }
    }
}

