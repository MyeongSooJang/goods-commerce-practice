package com.goods.payment.domain.service;

import com.goods.payment.infrastructure.messaging.kafka.contract.CardConfirmResultMessage;

public interface CardConfirmResultEventPublisher {

    void publish(CardConfirmResultMessage event);
}
