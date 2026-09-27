package com.goods.order.application.port.dto.response;

import com.goods.order.domain.enumtype.ProductOrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductInfo(
        UUID productId,
        UUID sellerId,
        String name,
        BigDecimal price,
        String thumbnailKeySnapshot,
        ProductOrderStatus productOrderStatus
) {
}
