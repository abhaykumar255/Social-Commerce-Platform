package com.social.product.dto;

import com.social.product.model.Product;
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
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private UUID id;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private UUID categoryId;
    private String categoryName;
    private String brand;
    private Integer stockQuantity;
    private Integer availableQuantity;
    private String status;
    private List<String> images;
    private BigDecimal weight;
    private String dimensions;
    private BigDecimal rating;
    private Integer reviewCount;
    private Long viewCount;
    private Long soldCount;
    private boolean featured;
    private boolean inStock;
    private boolean lowStock;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductDTO fromEntity(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .brand(product.getBrand())
                .stockQuantity(product.getStockQuantity())
                .availableQuantity(product.getAvailableQuantity())
                .status(product.getStatus().name())
                .images(product.getImages())
                .weight(product.getWeight())
                .dimensions(product.getDimensions())
                .rating(product.getRating())
                .reviewCount(product.getReviewCount())
                .viewCount(product.getViewCount())
                .soldCount(product.getSoldCount())
                .featured(product.isFeatured())
                .inStock(product.isInStock())
                .lowStock(product.isLowStock())
                .tags(product.getTags())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
