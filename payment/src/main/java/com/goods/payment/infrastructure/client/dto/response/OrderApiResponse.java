package com.goods.payment.infrastructure.client.dto.response;

public record OrderApiResponse<T>(
        boolean success,
        T data,
        OrderApiErrorResponse error
) {
}
