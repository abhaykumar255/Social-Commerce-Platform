package com.social.order.dto;

import com.social.order.model.BillingAddress;
import com.social.order.model.Order;
import com.social.order.model.ShippingAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private UUID id;
    private String orderNumber;
    private UUID userId;
    private List<OrderItemDTO> items;
    private String status;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal shippingCost;
    private BigDecimal discount;
    private BigDecimal total;
    private ShippingAddress shippingAddress;
    private BillingAddress billingAddress;
    private String paymentMethod;
    private UUID paymentId;
    private String notes;
    private LocalDateTime confirmedAt;
    private LocalDateTime shippedAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime cancelledAt;
    private String cancellationReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OrderDTO fromEntity(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .userId(order.getUserId())
                .items(order.getItems().stream()
                        .map(OrderItemDTO::fromEntity)
                        .collect(Collectors.toList()))
                .status(order.getStatus().name())
                .subtotal(order.getSubtotal())
                .tax(order.getTax())
                .shippingCost(order.getShippingCost())
                .discount(order.getDiscount())
                .total(order.getTotal())
                .shippingAddress(order.getShippingAddress())
                .billingAddress(order.getBillingAddress())
                .paymentMethod(order.getPaymentMethod())
                .paymentId(order.getPaymentId())
                .notes(order.getNotes())
                .confirmedAt(order.getConfirmedAt())
                .shippedAt(order.getShippedAt())
                .deliveredAt(order.getDeliveredAt())
                .cancelledAt(order.getCancelledAt())
                .cancellationReason(order.getCancellationReason())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
