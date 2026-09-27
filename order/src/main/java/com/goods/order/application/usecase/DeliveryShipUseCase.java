package com.goods.order.application.usecase;

import com.goods.order.presentation.dto.request.DeliveryShipRequest;
import com.goods.order.presentation.dto.response.DeliveryShipResponse;

import java.util.UUID;

public interface DeliveryShipUseCase {
    DeliveryShipResponse startShip(UUID deliveryId, UUID sellerId, DeliveryShipRequest request);
}
