package com.goods.order.application.port;

import com.goods.order.presentation.dto.response.DeliveryTrackingResponse;

public interface TrackingPort {
    DeliveryTrackingResponse getTrackingInfo(String courierCode, String invoiceNumber);
}
