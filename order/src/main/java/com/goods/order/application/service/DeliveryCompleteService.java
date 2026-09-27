package com.goods.order.application.service;

import com.goods.order.common.exception.CustomException;
import com.goods.order.common.exception.ErrorCode;
import com.goods.order.domain.entity.Delivery;
import com.goods.order.domain.entity.Order;
import com.goods.order.domain.entity.OrderItem;
import com.goods.order.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryCompleteService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public void complete(UUID deliveryId) {
        Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId)
                .orElseThrow(() -> new CustomException(ErrorCode.DELIVERY_NOT_FOUND));

        delivery.complete();

        OrderItem orderItem = delivery.getOrderItem();
        orderItem.deliver();

        Order order = orderItem.getOrder();
        order.markDelivered();

        log.info("배송 완료 처리. deliveryId={}, orderId={}", deliveryId, order.getOrderId());
    }
}
