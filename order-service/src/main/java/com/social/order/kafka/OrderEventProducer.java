package com.social.order.kafka;

import com.social.order.event.OrderCreatedEvent;
import com.social.order.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.social.common.constant.AppConstants.TOPIC_ORDER_CREATED;
import static com.social.common.constant.AppConstants.TOPIC_ORDER_EVENTS;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrderEvent(OrderEvent event) {
        try {
            kafkaTemplate.send(TOPIC_ORDER_EVENTS, event.getOrderId().toString(), event);
            log.info("Order event sent to Kafka: {}", event);
        } catch (Exception e) {
            log.error("Failed to send order event to Kafka: {}", e.getMessage());
        }
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        try{
            kafkaTemplate.send(TOPIC_ORDER_CREATED, event.getOrderId().toString(), event);
            log.info("Order created event sent to Kafka: {}", event);
        } catch (Exception e){
            log.error("Failed to send order created event to Kafka: {}", e.getMessage());
        }
    }

}
