package com.goods.order.presentation.dto.response;

public record DeliveryTrackingDetailResponse(
        String time,
        String location,
        String status
) {
}
