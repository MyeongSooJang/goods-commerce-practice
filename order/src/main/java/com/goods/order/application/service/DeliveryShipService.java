package com.goods.order.application.service;

import com.goods.order.application.usecase.DeliveryShipUseCase;
import com.goods.order.common.exception.CustomException;
import com.goods.order.common.exception.ErrorCode;
import com.goods.order.domain.entity.CourierCompany;
import com.goods.order.domain.entity.Delivery;
import com.goods.order.domain.entity.Order;
import com.goods.order.domain.repository.CourierRepository;
import com.goods.order.domain.repository.DeliveryRepository;
import com.goods.order.infrastructure.fake.FakeWebhookTrigger;
import com.goods.order.presentation.dto.request.DeliveryShipRequest;
import com.goods.order.presentation.dto.response.DeliveryShipResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryShipService implements DeliveryShipUseCase {

    private final DeliveryRepository deliveryRepository;
    private final CourierRepository courierRepository;
    private final FakeWebhookTrigger fakeWebhookTrigger;

    @Transactional
    public DeliveryShipResponse startShip(UUID deliveryId, UUID sellerId, DeliveryShipRequest request) {
        Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId)
                .orElseThrow(() -> new CustomException(ErrorCode.DELIVERY_NOT_FOUND));

        if (!delivery.getSellerId().equals(sellerId)) {
            throw new CustomException(ErrorCode.ORDER_FORBIDDEN);
        }

        CourierCompany courier = courierRepository.findByNameAndActiveTrue(request.courier())
                .orElseThrow(() -> new CustomException(ErrorCode.COURIER_NOT_FOUND));

        delivery.ship(courier.getCode(), request.invoiceNumber());
        delivery.getOrderItem().startShip();

        Order order = delivery.getOrderItem().getOrder();
        order.markShipping();

        log.info("배송 시작 - deliveryId: {}, courier: {}, invoiceNumber: {}", deliveryId, courier.getName(), request.invoiceNumber());

        fakeWebhookTrigger.scheduleDeliveryComplete(deliveryId);

        return DeliveryShipResponse.from(delivery, courier.getName());
    }
}
