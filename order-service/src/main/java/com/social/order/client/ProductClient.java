package com.social.order.client;

import com.social.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.UUID;

@FeignClient(name = "product-service", path = "/api/v1/products")
public interface ProductClient {

    @GetMapping("/{id}")
    ApiResponse<ProductDTO> getProductById(@PathVariable UUID id);

    @PatchMapping("/{id}/stock")
    ApiResponse<ProductDTO> updateStock(@PathVariable UUID id, @RequestBody Map<String, Object> request);

    // ProductDTO inner class for Feign response
    class ProductDTO {
        public UUID id;
        public String sku;
        public String name;
        public String description;
        public java.math.BigDecimal price;
        public java.math.BigDecimal discountPrice;
        public Integer stockQuantity;
        public Integer availableQuantity;
        public String status;
        public java.util.List<String> images;
        public boolean inStock;
    }
}
