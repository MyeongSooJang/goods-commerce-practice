package com.goods.payment.application.dto;

import com.goods.payment.domain.enumtype.ChargeStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record ChargeConfirmFailureResult(
        UUID chargeId,
        ChargeStatus chargeStatus,
        String orderId,
        String failureReason,
        LocalDateTime failedAt
) {
}
