package com.social.order.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddress {

    @Column(name = "shipping_full_name", length = 100)
    private String fullName;

    @Column(name = "shipping_phone", length = 20)
    private String phone;

    @Column(name = "shipping_address_line1", length = 200)
    private String addressLine1;

    @Column(name = "shipping_address_line2", length = 200)
    private String addressLine2;

    @Column(name = "shipping_city", length = 100)
    private String city;

    @Column(name = "shipping_state", length = 100)
    private String state;

    @Column(name = "shipping_postal_code", length = 20)
    private String postalCode;

    @Column(name = "shipping_country", length = 100)
    private String country;
}
