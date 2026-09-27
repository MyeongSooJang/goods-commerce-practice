package com.goods.order.application.port.dto.request;

import java.util.UUID;

public record ProductStockDeductRequest(
        UUID productId,
        Integer quantity
) {
}
