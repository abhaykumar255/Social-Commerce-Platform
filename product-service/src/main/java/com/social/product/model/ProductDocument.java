package com.social.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "products")
public class ProductDocument {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String sku;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String name;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Double)
    private BigDecimal discountPrice;

    @Field(type = FieldType.Keyword)
    private String categoryId;

    @Field(type = FieldType.Text)
    private String categoryName;

    @Field(type = FieldType.Keyword)
    private String brand;

    @Field(type = FieldType.Integer)
    private Integer stockQuantity;

    @Field(type = FieldType.Keyword)
    private String status;

    @Field(type = FieldType.Keyword)
    private List<String> images;

    @Field(type = FieldType.Double)
    private BigDecimal rating;

    @Field(type = FieldType.Integer)
    private Integer reviewCount;

    @Field(type = FieldType.Long)
    private Long viewCount;

    @Field(type = FieldType.Long)
    private Long soldCount;

    @Field(type = FieldType.Boolean)
    private boolean featured;

    @Field(type = FieldType.Keyword)
    private List<String> tags;

    public static ProductDocument fromProduct(Product product) {
        return ProductDocument.builder()
                .id(product.getId().toString())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .categoryId(product.getCategory() != null ? product.getCategory().getId().toString() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .brand(product.getBrand())
                .stockQuantity(product.getStockQuantity())
                .status(product.getStatus().name())
                .images(product.getImages())
                .rating(product.getRating())
                .reviewCount(product.getReviewCount())
                .viewCount(product.getViewCount())
                .soldCount(product.getSoldCount())
                .featured(product.isFeatured())
                .tags(product.getTags())
                .build();
    }

}
