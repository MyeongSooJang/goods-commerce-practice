package com.goods.order.infrastructure.client.dto.request;

import java.util.UUID;

public record ExternalSellerRefundLineRequest(
        UUID orderItemId
) {
}
