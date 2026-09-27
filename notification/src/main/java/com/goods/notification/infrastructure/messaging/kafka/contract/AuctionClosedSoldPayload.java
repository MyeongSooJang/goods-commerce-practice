package com.goods.notification.infrastructure.messaging.kafka.contract;

import java.math.BigDecimal;

public record AuctionClosedSoldPayload(
        String auctionTitle,
        BigDecimal finalPrice
) {
}
