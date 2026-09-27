package com.goods.member.application.dto.result;

import java.time.LocalDateTime;

public record AccountVerificationCancelResult(
        String sessionId,
        String status,
        LocalDateTime cancelledAt
) {
}
