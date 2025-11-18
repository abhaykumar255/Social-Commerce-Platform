package com.social.order.dto;

import com.social.order.model.BillingAddress;
import com.social.order.model.ShippingAddress;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotNull(message = "Shipping address is required")
    private ShippingAddress shippingAddress;

    @NotNull(message = "Billing address is required")
    private BillingAddress billingAddress;

    private String paymentMethod;

    private BigDecimal shippingCost;

    private BigDecimal discount;

    private String notes;
}
