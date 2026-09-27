package com.goods.notification.infrastructure.messaging.kafka.dlq.model;

public enum NotificationConsumerFailureAction {
    DLQ,
    RETRY,
    IGNORE
}
