package com.goods.member.presentation.dto;

import java.time.LocalDateTime;

public record EmailVerificationSendResponse(
        String email,
        String purpose,
        String status,
        LocalDateTime expiresAt
) {
}

