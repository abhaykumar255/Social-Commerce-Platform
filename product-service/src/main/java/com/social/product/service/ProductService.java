package com.social.product.service;

import com.social.common.dto.PageResponse;
import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.product.dto.CreateProductRequest;
import com.social.product.dto.ProductDTO;
import com.social.product.dto.UpdateProductRequest;
import com.social.product.dto.UpdateStockRequest;
import com.social.product.model.Category;
import com.social.product.model.Product;
import com.social.product.model.ProductDocument;
import com.social.product.repository.CategoryRepository;
import com.social.product.repository.ProductDocumentRepository;
import com.social.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductDocumentRepository productDocumentRepository;

    @Transactional
    public ProductDTO createProduct(CreateProductRequest request){
        log.info("Creating product with SKU: {}", request.getSku());

        if (productRepository.existsBySku(request.getSku()))
            throw new ValidationException("Product with SKU " + request.getSku() + " already exists");

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));

        Product product = new Product();
        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setDiscountPrice(request.getDiscountPrice());
        product.setCostPrice(request.getCostPrice());
        product.setCategory(category);
        product.setBrand(request.getBrand());
        product.setStockQuantity(request.getStockQuantity());
        product.setLowStockThreshold(request.getLowStockThreshold() != null ? request.getLowStockThreshold() : 10);
        product.setImages(request.getImages());
        product.setWeight(request.getWeight());
        product.setDimensions(request.getDimensions());
        product.setFeatured(request.isFeatured());
        product.setTags(request.getTags());

        Product savedProduct = productRepository.save(product);
        indexProduct(savedProduct);

        log.info("Product created successfully with ID: {}", savedProduct.getId());
        return ProductDTO.fromEntity(savedProduct);
    }

    @Cacheable(value = "products", key = "#id")
    public ProductDTO getProductById(UUID id) {
        log.info("Fetching product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        // Increment view count
        product.setViewCount(product.getViewCount() + 1);
        productRepository.save(product);

        return ProductDTO.fromEntity(product);
    }

    public ProductDTO getProductBySku(String sku) {
        log.info("Fetching product with SKU: {}", sku);
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with SKU: " + sku));
        return ProductDTO.fromEntity(product);
    }

    public PageResponse<ProductDTO> getAllProducts(int page, int size, String sortBy, String sortDir) {
        log.info("Fetching all products - page: {}, size: {}", page, size);

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findByDeletedFalse(pageable);

        return PageResponse.of(
                productPage.getContent().stream().map(ProductDTO::fromEntity).toList(),
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
    }

    public PageResponse<ProductDTO> getProductsByCategory(UUID categoryId, int page, int size) {
        log.info("Fetching products for category: {}", categoryId);

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByCategoryId(categoryId, pageable);

        return PageResponse.of(
                productPage.getContent().stream().map(ProductDTO::fromEntity).toList(),
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
    }

    public PageResponse<ProductDTO> searchProducts(String query, int page, int size) {
        log.info("Searching products with query: {}", query);

        Pageable pageable = PageRequest.of(page, size);

        // Use Elasticsearch for search
        Page<ProductDocument> documentPage = productDocumentRepository.searchProducts(query, pageable);

        List<ProductDTO> products = documentPage.getContent().stream()
                .map(doc -> {
                    Product product = productRepository.findById(UUID.fromString(doc.getId())).orElse(null);
                    return product != null ? ProductDTO.fromEntity(product) : null;
                })
                .filter(Objects::nonNull)
                .toList();

        return PageResponse.of(
                products,
                documentPage.getNumber(),
                documentPage.getSize(),
                documentPage.getTotalElements(),
                documentPage.getTotalPages(),
                documentPage.isLast()
        );
    }

    public PageResponse<ProductDTO> getFeaturedProducts(int page, int size) {
        log.info("Fetching featured products");

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findFeaturedProducts(pageable);

        return PageResponse.of(
                productPage.getContent().stream().map(ProductDTO::fromEntity).toList(),
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
    }

    @Transactional
    @CacheEvict(value = "products", key = "#id")
    public ProductDTO updateProduct(UUID id, UpdateProductRequest request) {
        log.info("Updating product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        // Update fields if provided
        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }
        if (request.getDiscountPrice() != null) {
            product.setDiscountPrice(request.getDiscountPrice());
        }
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            product.setCategory(category);
        }
        if (request.getBrand() != null) {
            product.setBrand(request.getBrand());
        }
        if (request.getImages() != null) {
            product.setImages(request.getImages());
        }
        if (request.getWeight() != null) {
            product.setWeight(request.getWeight());
        }
        if (request.getDimensions() != null) {
            product.setDimensions(request.getDimensions());
        }
        if (request.getFeatured() != null) {
            product.setFeatured(request.getFeatured());
        }
        if (request.getTags() != null) {
            product.setTags(request.getTags());
        }

        Product updatedProduct = productRepository.save(product);

        // Update Elasticsearch index
        indexProduct(updatedProduct);

        log.info("Product updated successfully with ID: {}", updatedProduct.getId());
        return ProductDTO.fromEntity(updatedProduct);
    }

    @Transactional
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(UUID id) {
        log.info("Deleting product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        // Soft delete
        product.setDeleted(true);
        product.setStatus(Product.ProductStatus.DISCONTINUED);
        productRepository.save(product);

        // Remove from Elasticsearch
        productDocumentRepository.deleteById(id.toString());
        productRepository.delete(product);

        log.info("Product deleted successfully with ID: {}", id);
    }

    @Transactional
    public ProductDTO updateStock(UUID id, UpdateStockRequest request) {
        log.info("Updating stock for product ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        switch (request.getOperation().toUpperCase()) {
            case "ADD":
                product.setStockQuantity(product.getStockQuantity() + request.getQuantity());
                break;
            case "SUBTRACT":
                if (product.getStockQuantity() < request.getQuantity()) {
                    throw new ValidationException("Insufficient stock");
                }
                product.setStockQuantity(product.getStockQuantity() - request.getQuantity());
                break;
            case "SET":
                product.setStockQuantity(request.getQuantity());
                break;
            default:
                throw new ValidationException("Invalid operation: " + request.getOperation());
        }

        // Update status based on stock
        if (product.getAvailableQuantity() == 0) {
            product.setStatus(Product.ProductStatus.OUT_OF_STOCK);
        } else if (product.getStatus() == Product.ProductStatus.OUT_OF_STOCK) {
            product.setStatus(Product.ProductStatus.ACTIVE);
        }

        Product updatedProduct = productRepository.save(product);
        indexProduct(updatedProduct);

        log.info("Stock updated successfully for product ID: {}", id);
        return ProductDTO.fromEntity(updatedProduct);
    }

    @Transactional
    public void reserveStock(UUID id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (product.getAvailableQuantity() < quantity) {
            throw new ValidationException("Insufficient stock available");
        }

        product.setReservedQuantity(product.getReservedQuantity() + quantity);
        productRepository.save(product);
    }

    @Transactional
    public void releaseStock(UUID id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setReservedQuantity(Math.max(0, product.getReservedQuantity() - quantity));
        productRepository.save(product);
    }

    public List<ProductDTO> getLowStockProducts() {
        return productRepository.findLowStockProducts().stream()
                .map(ProductDTO::fromEntity)
                .toList();
    }

    public long getActiveProductCount() {
        return productRepository.countActiveProducts();
    }

    private void indexProduct(Product product){
        try {
            ProductDocument document = ProductDocument.fromProduct(product);
            productDocumentRepository.save(document);
            log.info("Product indexed to Elasticsearch: {}", product.getId());

        } catch (Exception e){
            log.error("Failed to index product to ElasticSearch: {}", e.getMessage());
        }
    }
}
