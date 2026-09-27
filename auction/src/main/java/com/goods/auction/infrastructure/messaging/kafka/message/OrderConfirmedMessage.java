package com.goods.auction.infrastructure.messaging.kafka.message;

import java.util.UUID;

public record OrderConfirmedMessage(
        UUID auctionId,
        UUID productId
) {}
