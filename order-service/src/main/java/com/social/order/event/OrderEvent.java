package com.social.order.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEvent {

    private UUID orderId;
    private String orderNumber;
    private UUID userId;
    private String eventType; // CREATED, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    private BigDecimal total;
    private String status;
    private LocalDateTime timestamp;
}
