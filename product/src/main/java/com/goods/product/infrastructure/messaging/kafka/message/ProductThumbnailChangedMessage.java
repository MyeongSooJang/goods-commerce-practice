package com.goods.product.infrastructure.messaging.kafka.message;

public record ProductThumbnailChangedMessage(
        String eventId,
        String productId,
        String thumbnailKey,
        String occurredAt
) {
}
