package com.goods.notification.infrastructure.messaging.kafka.contract;

public record AuctionClosedUnsoldPayload(
        String auctionTitle
) {
}
