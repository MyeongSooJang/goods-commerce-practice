package com.goods.order.application.usecase;

import com.goods.order.presentation.dto.request.OrderCancelRequest;
import com.goods.order.presentation.dto.response.OrderCancelResponse;

import java.util.UUID;

public interface OrderCancelUseCase {

    OrderCancelResponse cancelOrder(UUID orderId, UUID memberId, OrderCancelRequest request);
}
