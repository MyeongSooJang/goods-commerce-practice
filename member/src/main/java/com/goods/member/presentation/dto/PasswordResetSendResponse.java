package com.goods.member.presentation.dto;

import com.goods.member.application.dto.result.PasswordResetSendResult;

public record PasswordResetSendResponse(
        String message
) {
    public static PasswordResetSendResponse from(PasswordResetSendResult result) {
        return new PasswordResetSendResponse(result.message());
    }
}

