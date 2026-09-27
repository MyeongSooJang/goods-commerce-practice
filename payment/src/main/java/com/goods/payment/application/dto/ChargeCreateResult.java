package com.goods.payment.application.dto;

import com.goods.payment.domain.enumtype.ChargeStatus;
import java.math.BigDecimal;
import java.util.UUID;

public record ChargeCreateResult(
        UUID chargeId,
        UUID walletId,
        String pgOrderId,
        BigDecimal amount,
        ChargeStatus chargeStatus
) {
}
