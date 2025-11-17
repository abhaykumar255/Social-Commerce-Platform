package com.social.product.dto;

import jakarta.validation.constraints.*;
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
public class CreateProductRequest {

    @NotBlank(message = "SKU is required")
    @Size(max = 50, message = "SKU must not exceed 50 characters")
    private String sku;

    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 200, message = "Product name must be between 3 and 200 characters")
    private String name;

    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @DecimalMin(value = "0.00", message = "Discount price must be non-negative")
    private BigDecimal discountPrice;

    @DecimalMin(value = "0.00", message = "Cost price must be non-negative")
    private BigDecimal costPrice;

    @NotNull(message = "Category is required")
    private UUID categoryId;

    @Size(max = 100, message = "Brand must not exceed 100 characters")
    private String brand;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity must be non-negative")
    private Integer stockQuantity;

    @Min(value = 0, message = "Low stock threshold must be non-negative")
    private Integer lowStockThreshold;

    private List<String> images;

    @DecimalMin(value = "0.00", message = "Weight must be non-negative")
    private BigDecimal weight;

    @Size(max = 100, message = "Dimensions must not exceed 100 characters")
    private String dimensions;

    private boolean featured;

    private List<String> tags;
}
