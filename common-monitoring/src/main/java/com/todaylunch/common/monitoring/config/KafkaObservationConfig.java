package com.todaylunch.common.monitoring.config;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@AutoConfiguration
@ConditionalOnClass(name = "org.springframework.kafka.core.KafkaTemplate")
public class KafkaObservationConfig {

    @Bean
    public BeanPostProcessor kafkaTemplateObservationConfigurer(ObservationRegistry registry) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) {
                if (bean instanceof KafkaTemplate<?, ?> kafkaTemplate) {
                    kafkaTemplate.setObservationEnabled(true);
                    kafkaTemplate.setObservationRegistry(registry);
                }
                return bean;
            }
        };
    }
}
