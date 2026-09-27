package com.goods.member.infrastructure.client.dto.response;

public record DeliveryStatusCountResponse(
        long preparing,
        long shipped,
        long delivered
) {
}
