package com.goods.ai.infrastructure.messaging.kafka.dlq;

public interface ProductEventDlqPublisher {

    void publish(String listenerName, String topic, String rawMessage, Exception exception);
}
