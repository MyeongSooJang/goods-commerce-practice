package com.goods.order.application.port.dto.request;

import com.goods.order.domain.enumtype.PaymentRefundType;

import java.util.List;
import java.util.UUID;

public record SellerRefundRequest(
        UUID orderId,
        UUID orderCancelRequestId,
        PaymentRefundType refundType,
        String reason,
        List<UUID> orderItemIds
) {
}
