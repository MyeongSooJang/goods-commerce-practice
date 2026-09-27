package com.goods.member.presentation.dto;

import com.goods.member.application.dto.result.PasswordResetConfirmResult;

public record PasswordResetConfirmResponse(
        String message
) {
    public static PasswordResetConfirmResponse from(PasswordResetConfirmResult result) {
        return new PasswordResetConfirmResponse(result.message());
    }
}

