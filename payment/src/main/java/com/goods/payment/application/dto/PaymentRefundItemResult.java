package com.goods.payment.application.dto;

import com.goods.payment.domain.enumtype.PaymentRefundItemStatus;
import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRefundItemResult(
        UUID orderItemId,
        PaymentRefundItemStatus status,
        BigDecimal refundAmount
) {
}
