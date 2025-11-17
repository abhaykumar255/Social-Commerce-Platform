package com.social.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

    @Size(min = 3, max = 200, message = "Product name must be between 3 and 200 characters")
    private String name;

    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @DecimalMin(value = "0.00", message = "Discount price must be non-negative")
    private BigDecimal discountPrice;

    private UUID categoryId;

    @Size(max = 100, message = "Brand must not exceed 100 characters")
    private String brand;

    private List<String> images;

    @DecimalMin(value = "0.00", message = "Weight must be non-negative")
    private BigDecimal weight;

    @Size(max = 100, message = "Dimensions must not exceed 100 characters")
    private String dimensions;

    private Boolean featured;

    private List<String> tags;
}
