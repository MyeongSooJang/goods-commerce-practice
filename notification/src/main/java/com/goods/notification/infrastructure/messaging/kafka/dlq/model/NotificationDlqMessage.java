package com.goods.notification.infrastructure.messaging.kafka.dlq.model;

import java.time.Instant;

public record NotificationDlqMessage(
        String listenerName,
        String reason,
        String exceptionType,
        String exceptionMessage,
        String rawMessage,
        Instant failedAt
) {
}
