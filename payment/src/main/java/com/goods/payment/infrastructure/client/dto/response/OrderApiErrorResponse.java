package com.goods.payment.infrastructure.client.dto.response;

public record OrderApiErrorResponse(
        String code,
        String message
) {
}
