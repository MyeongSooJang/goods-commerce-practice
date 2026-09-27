package com.goods.order.application.service;

import com.goods.order.application.processor.PaymentProcessor;
import com.goods.order.application.usecase.AuctionWinAcceptUseCase;
import com.goods.order.common.exception.CustomException;
import com.goods.order.common.exception.ErrorCode;
import com.goods.order.domain.entity.Order;
import com.goods.order.domain.entity.OutboxEvent;
import com.goods.order.domain.repository.OrderRepository;
import com.goods.order.domain.repository.OutboxRepository;
import com.goods.order.infrastructure.kafka.KafkaTopics;
import com.goods.order.infrastructure.kafka.OutboxEventSaver;
import com.goods.order.infrastructure.kafka.event.OrderConfirmedEvent;
import com.goods.order.infrastructure.kafka.event.PaymentFailedEvent;
import com.goods.order.presentation.dto.request.AuctionWinAcceptRequest;
import com.goods.order.presentation.dto.response.OrderCreateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuctionWinAcceptService implements AuctionWinAcceptUseCase {

    private final OrderRepository orderRepository;
    private final OutboxRepository outboxRepository;
    private final OutboxEventSaver outboxEventSaver;
    private final ObjectMapper objectMapper;
    private final PaymentProcessor paymentProcessor;
    private final DeliveryCreateService deliveryCreateService;

    @Transactional
    @Override
    public OrderCreateResponse acceptWinByDeposit(UUID memberId, AuctionWinAcceptRequest request) {
        Order order = findAndValidate(memberId, request.orderId());

        order.assignShippingInfo(
                request.address(),
                request.addressDetail(),
                request.zipCode(),
                request.receiver(),
                request.receiverPhone()
        );

        try {
            paymentProcessor.process(order);
        } catch (CustomException e) {
            log.warn("경매 결제 실패. orderId={}", order.getOrderId());
            savePaymentFailedOutbox(order);
            throw e;
        }

        order.confirm();
        deliveryCreateService.create(order);
        saveOrderConfirmedOutbox(order);
        return OrderCreateResponse.from(order);
    }

    @Transactional
    @Override
    public OrderCreateResponse acceptWinByPg(UUID memberId, AuctionWinAcceptRequest request) {
        Order order = findAndValidate(memberId, request.orderId());

        order.assignShippingInfo(
                request.address(),
                request.addressDetail(),
                request.zipCode(),
                request.receiver(),
                request.receiverPhone()
        );
        return OrderCreateResponse.from(order);
    }

    private Order findAndValidate(UUID memberId, UUID orderId) {
        Order order = orderRepository.findByOrderIdAndBuyerId(orderId, memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.ORDER_NOT_FOUND));

        if (order.isShippingInfoAssigned()) {
            throw new CustomException(ErrorCode.ORDER_ALREADY_ACCEPTED);
        }

        return order;
    }

    private void saveOrderConfirmedOutbox(Order order) {
        try {
            String payload = objectMapper.writeValueAsString(OrderConfirmedEvent.envelopeOf(order));
            outboxRepository.save(OutboxEvent.create(KafkaTopics.ORDER_CONFIRMED, order.getOrderId().toString(), payload));
        } catch (Exception e) {
            log.error("OrderConfirmedEvent Outbox 저장 실패. orderId={}", order.getOrderId(), e);
        }
    }

    private void savePaymentFailedOutbox(Order order) {
        try {
            String payload = objectMapper.writeValueAsString(PaymentFailedEvent.envelopeOf(order));
            outboxEventSaver.save(OutboxEvent.create(KafkaTopics.PAYMENT_FAILED, order.getOrderId().toString(), payload));
        } catch (Exception e) {
            log.error("PaymentFailedEvent Outbox 저장 실패. orderId={}", order.getOrderId(), e);
        }
    }
}
