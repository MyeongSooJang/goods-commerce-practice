package com.goods.member.infrastructure.client.dto.response;

public record ApiResponse<T>(
        boolean success,
        T data,
        Object error
) {
}
