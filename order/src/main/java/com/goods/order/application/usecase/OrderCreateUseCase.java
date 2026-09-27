package com.goods.order.application.usecase;

import com.goods.order.presentation.dto.request.OrderCreateRequest;
import com.goods.order.presentation.dto.response.OrderCreateResponse;

import java.util.UUID;

public interface OrderCreateUseCase {

    OrderCreateResponse createByDeposit(UUID memberId, OrderCreateRequest request);

    OrderCreateResponse createByPg(UUID memberId, OrderCreateRequest request);
}
