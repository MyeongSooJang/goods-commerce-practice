package com.goods.order.application.port.dto.response;

import com.goods.order.domain.enumtype.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentResult(
        UUID orderId,
        BigDecimal paidAmount,
        PaymentStatus status,
        String reasonCode
) {
}
