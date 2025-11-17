package com.social.product.repository;

import com.social.product.model.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.elasticsearch.annotations.Query;

import java.math.BigDecimal;

@Repository
public interface ProductDocumentRepository extends ElasticsearchRepository<ProductDocument, String> {

    Page<ProductDocument> findByNameContainingOrDescriptionContaining(
            String name, String description, Pageable pageable);

    Page<ProductDocument> findByBrand(String brand, Pageable pageable);

    Page<ProductDocument> findByCategoryId(String categoryId, Pageable pageable);

    Page<ProductDocument> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    Page<ProductDocument> findByFeaturedTrue(Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"name^3\", \"description\", \"brand^2\", \"tags\"]}}]}}")
    Page<ProductDocument> searchProducts(String query, Pageable pageable);
}
