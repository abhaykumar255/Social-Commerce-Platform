package com.social.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.social.common.constant.AppConstants.TOPIC_ORDER_CREATED;
import static com.social.common.constant.AppConstants.TOPIC_ORDER_EVENTS;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic orderEventsTopic(){
        return TopicBuilder.name(TOPIC_ORDER_EVENTS)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderCreatedTopic(){
        return TopicBuilder.name(TOPIC_ORDER_CREATED)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
