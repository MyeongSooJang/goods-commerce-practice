package com.example.notification.infrastructure.config;

import com.example.notification.infrastructure.messaging.kafka.dlq.exception.EventParseException;
import com.example.notification.infrastructure.messaging.kafka.dlq.exception.InvalidEventPayloadException;
import com.example.notification.infrastructure.messaging.kafka.dlq.exception.UnsupportedEventTypeException;
import com.example.notification.infrastructure.messaging.kafka.dlq.model.NotificationConsumerFailureDecision;
import com.example.notification.infrastructure.messaging.kafka.dlq.model.NotificationDlqReason;
import com.example.notification.infrastructure.messaging.kafka.dlq.publisher.NotificationDlqPublisher;
import com.todaylunch.common.messaging.kafka.KafkaConsumerProps;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class NotificationKafkaConsumerConfig {

    private static final String UNIFIED_NOTIFICATION_LISTENER = "listenNotificationEvent";

    @Bean
    public ConsumerFactory<String, String> notificationConsumerFactory(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${notification.kafka.consumer-groups.member-signed-up:notification-service}") String groupId
    ) {
        return new DefaultKafkaConsumerFactory<>(
                KafkaConsumerProps.defaults(bootstrapServers, groupId)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> notificationKafkaListenerContainerFactory(
            ConsumerFactory<String, String> notificationConsumerFactory,
            DefaultErrorHandler notificationKafkaErrorHandler
    ) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(notificationConsumerFactory);
        factory.setCommonErrorHandler(notificationKafkaErrorHandler);
        return factory;
    }

    @Bean
    public ConsumerRecordRecoverer notificationDlqRecoverer(
            NotificationDlqPublisher notificationDlqPublisher
    ) {
        return (ConsumerRecord<?, ?> record, Exception exception) -> {
            String rawMessage = record.value() == null ? "" : record.value().toString();
            notificationDlqPublisher.publish(
                    UNIFIED_NOTIFICATION_LISTENER,
                    rawMessage,
                    exception instanceof RuntimeException runtimeException
                            ? runtimeException
                            : new RuntimeException(exception),
                    NotificationConsumerFailureDecision.dlq(NotificationDlqReason.TEMPORARY_PROCESSING_ERROR)
            );
        };
    }

    @Bean
    public DefaultErrorHandler notificationKafkaErrorHandler(
            ConsumerRecordRecoverer notificationDlqRecoverer,
            @Value("${notification.kafka.retry.initial-interval-ms:1000}") long initialIntervalMs,
            @Value("${notification.kafka.retry.max-attempts:3}") long maxAttempts
    ) {
        DefaultErrorHandler errorHandler =
                new DefaultErrorHandler(notificationDlqRecoverer, new FixedBackOff(initialIntervalMs, maxAttempts));
        errorHandler.addNotRetryableExceptions(
                EventParseException.class,
                InvalidEventPayloadException.class,
                UnsupportedEventTypeException.class,
                IllegalArgumentException.class
        );
        return errorHandler;
    }
}
