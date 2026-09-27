package com.goods.order.application.usecase;

import com.goods.order.presentation.dto.response.DeliveryTrackingResponse;

import java.util.UUID;

public interface DeliveryTrackingUseCase {
    DeliveryTrackingResponse getTrackingInfo(UUID deliveryId, UUID memberId);
}
