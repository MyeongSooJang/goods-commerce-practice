package com.goods.ai.infrastructure.messaging.kafka.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductDeletedMessage(
        String eventId,
        String productId,
        String sourceUpdatedAt,
        String updatedAt,
        String occurredAt
) {
}

