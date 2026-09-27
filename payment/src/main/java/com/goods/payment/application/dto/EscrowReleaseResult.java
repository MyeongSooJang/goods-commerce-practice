package com.goods.payment.application.dto;

import com.goods.payment.domain.enumtype.EscrowStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record EscrowReleaseResult(
        UUID orderId,
        BigDecimal releasedAmount,
        EscrowStatus escrowStatus,
        LocalDateTime releasedAt
) {
}
