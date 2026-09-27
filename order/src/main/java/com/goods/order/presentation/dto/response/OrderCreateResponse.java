package com.goods.order.presentation.dto.response;

import com.goods.order.domain.entity.Order;
import com.goods.order.domain.enumtype.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderCreateResponse(
        UUID orderId,
        String orderNumber,
        BigDecimal totalPrice,
        OrderStatus status,
        LocalDateTime createdAt) {

    public static OrderCreateResponse from(Order order) {
        return new OrderCreateResponse(
                order.getOrderId(),
                order.getOrderNumber(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getCreatedAt());
    }
}