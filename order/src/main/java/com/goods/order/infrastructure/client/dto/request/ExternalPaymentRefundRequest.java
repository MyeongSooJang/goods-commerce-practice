package com.goods.order.infrastructure.client.dto.request;

import com.goods.order.domain.enumtype.PaymentRefundType;

import java.util.List;
import java.util.UUID;

public record ExternalPaymentRefundRequest(
        UUID orderId,
        UUID buyerMemberId,
        UUID orderCancelRequestId,
        PaymentRefundType refundType,
        String reason,
        List<ExternalPaymentRefundLineRequest> items
) {
}
