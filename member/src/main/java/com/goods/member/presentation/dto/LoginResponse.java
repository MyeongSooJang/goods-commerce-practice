package com.goods.member.presentation.dto;

import com.goods.member.application.dto.result.AuthTokenResult;
import java.util.UUID;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        long accessTokenExpiresIn,
        long refreshTokenExpiresIn,
        UUID sessionId
) {
    public static LoginResponse from(AuthTokenResult result) {
        return new LoginResponse(
                result.accessToken(),
                result.refreshToken(),
                result.tokenType(),
                result.accessTokenExpiresIn(),
                result.refreshTokenExpiresIn(),
                result.sessionId()
        );
    }
}

