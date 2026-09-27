package com.goods.member.presentation.dto;

import com.goods.member.application.dto.result.AuthSessionListResult;

public record AuthSessionListResponse(
        java.util.List<AuthSessionResponse> sessions
) {
    public static AuthSessionListResponse from(AuthSessionListResult result) {
        return new AuthSessionListResponse(
                result.sessions().stream()
                        .map(AuthSessionResponse::from)
                        .toList()
        );
    }
}
