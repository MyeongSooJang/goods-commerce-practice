package com.goods.notification.infrastructure.messaging.kafka.contract;

import java.util.UUID;

public record SellerPromotedPayload(
        UUID memberId,
        UUID sellerId,
        String bankName
) {
}
