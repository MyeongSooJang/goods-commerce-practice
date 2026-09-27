package com.goods.order.infrastructure.client.dto.request;

import com.goods.order.domain.enumtype.PaymentRefundType;

import java.util.List;
import java.util.UUID;

public record ExternalSellerRefundRequest(
        UUID orderId,
        UUID orderCancelRequestId,
        PaymentRefundType refundType,
        String reason,
        List<ExternalSellerRefundLineRequest> items
) {
}
