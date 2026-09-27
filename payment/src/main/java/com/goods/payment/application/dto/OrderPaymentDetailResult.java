package com.goods.payment.application.dto;

import com.goods.payment.domain.enumtype.OrderPaymentMethod;
import com.goods.payment.domain.enumtype.OrderPaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderPaymentDetailResult(
        UUID orderPaymentId,
        UUID orderId,
        BigDecimal totalAmount,
        OrderPaymentMethod paymentMethod,
        OrderPaymentStatus paymentStatus,
        LocalDateTime paidAt
) {
}
