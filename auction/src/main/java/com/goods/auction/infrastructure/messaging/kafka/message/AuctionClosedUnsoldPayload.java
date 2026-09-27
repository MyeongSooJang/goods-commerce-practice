package com.goods.auction.infrastructure.messaging.kafka.message;

public record AuctionClosedUnsoldPayload(
        String auctionTitle
) {}
