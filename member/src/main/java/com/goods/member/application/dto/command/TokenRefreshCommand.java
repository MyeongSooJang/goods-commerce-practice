package com.goods.member.application.dto.command;

public record TokenRefreshCommand(
        String refreshToken
) {
}
