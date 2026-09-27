package com.goods.order.presentation.dto.response;

import com.goods.order.domain.entity.Delivery;
import com.goods.order.domain.enumtype.DeliveryStatus;

public record DeliveryShipResponse(
        String courier,
        String courierCode,
        String invoiceNumber,
        DeliveryStatus status
) {
    public static DeliveryShipResponse from(Delivery delivery, String courierName) {
        return new DeliveryShipResponse(
                courierName,
                delivery.getCourierCode(),
                delivery.getInvoiceNumber(),
                delivery.getStatus()
        );
    }
}
