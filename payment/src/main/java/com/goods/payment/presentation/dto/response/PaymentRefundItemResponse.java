package com.goods.payment.presentation.dto.response;

import com.goods.payment.application.dto.PaymentRefundItemResult;
import com.goods.payment.domain.enumtype.PaymentRefundItemStatus;
import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRefundItemResponse(
        UUID orderItemId,
        PaymentRefundItemStatus status,
        BigDecimal refundAmount
) {
    public static PaymentRefundItemResponse from(PaymentRefundItemResult result) {
        return new PaymentRefundItemResponse(
                result.orderItemId(),
                result.status(),
                result.refundAmount()
        );
    }
}
