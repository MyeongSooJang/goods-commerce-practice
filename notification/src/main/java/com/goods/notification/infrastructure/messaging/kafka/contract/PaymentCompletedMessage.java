package com.goods.notification.infrastructure.messaging.kafka.contract;

import java.util.UUID;

public record PaymentCompletedMessage(
        UUID paymentId,
        UUID orderId,
        Long amount
) {
}
