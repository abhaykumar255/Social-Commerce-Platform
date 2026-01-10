package com.social.order.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {

    private UUID orderId;
    private String orderNumber;
    private UUID userId;
    private List<OrderItemEvent> items;
    private BigDecimal total;
    private LocalDateTime createdAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemEvent {
        private UUID productId;
        private Integer quantity;
        private BigDecimal unitPrice;
    }

}
