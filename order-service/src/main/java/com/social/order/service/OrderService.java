package com.social.order.service;

import com.social.common.exception.ValidationException;
import com.social.order.client.ProductClient;
import com.social.order.dto.CreateOrderRequest;
import com.social.order.dto.OrderDTO;
import com.social.order.event.OrderCreatedEvent;
import com.social.order.event.OrderEvent;
import com.social.order.kafka.OrderEventProducer;
import com.social.order.model.Order;
import com.social.order.repository.CartRepository;
import com.social.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductClient productClient;
    private final OrderEventProducer eventProducer;

    public long getUserOrderCount(UUID userId){
        return orderRepository.countByUserId(userId);
    }

    public BigDecimal getTotalRevenue(){
        return orderRepository.getTotalRevenue();
    }

    private String generateOrderNumber(){
        return "ORD-" + System.currentTimeMillis();
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "getProductDetailsFallback")
    private ProductClient.ProductDTO getProductDetails(UUID productId){
        return productClient.getProductById(productId).getData();
    }

    private ProductClient.ProductDTO getProductDetailsFallback(UUID productId, Exception e) {
        log.error("Failed to get product details: {}", e.getMessage());
        throw new ValidationException("Product service is unavailable");
    }

    private void publishOrderCreatedEvent(Order order) {
        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .userId(order.getUserId())
                .items(order.getItems().stream()
                        .map(item -> OrderCreatedEvent.OrderItemEvent.builder()
                                .productId(item.getProductId())
                                .quantity(item.getQuantity())
                                .unitPrice(item.getUnitPrice())
                                .build())
                        .collect(Collectors.toList()))
                .total(order.getTotal())
                .createdAt(order.getCreatedAt())
                .build();

        eventProducer.sendOrderCreatedEvent(event);
    }

    private void publishOrderEvent(Order order, String eventType) {
        OrderEvent event = OrderEvent.builder()
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .userId(order.getUserId())
                .eventType(eventType)
                .total(order.getTotal())
                .status(order.getStatus().name())
                .timestamp(LocalDateTime.now())
                .build();

        eventProducer.sendOrderEvent(event);
    }

    private OrderDTO createOrderFallback(UUID userId, CreateOrderRequest request, Exception e) {
        log.error("Failed to create order: {}", e.getMessage());
        throw new ValidationException("Order service is temporarily unavailable");
    }
}
