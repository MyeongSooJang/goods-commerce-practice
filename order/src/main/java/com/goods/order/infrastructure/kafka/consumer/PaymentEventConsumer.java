package com.goods.order.infrastructure.kafka.consumer;

import com.goods.order.application.service.OrderPaymentService;
import com.goods.order.infrastructure.kafka.KafkaTopics;
import com.goods.order.infrastructure.kafka.event.PaymentResultEvent;
import com.goods.common.event.contract.EventEnvelope;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventConsumer {

    private final OrderPaymentService orderPaymentService;

    @KafkaListener(topics = KafkaTopics.PAYMENT_RESULT, groupId = "order-group", containerFactory = "paymentListenerContainerFactory")
    public void consume(EventEnvelope<PaymentResultEvent> envelope) {
        orderPaymentService.handlePaymentResult(envelope.payload());
    }
}
