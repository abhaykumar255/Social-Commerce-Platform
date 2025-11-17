package com.social.product.service;

import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.product.dto.CategoryDTO;
import com.social.product.dto.CreateCategoryRequest;
import com.social.product.model.Category;
import com.social.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryDTO createCategory(CreateCategoryRequest request) {
        log.info("Creating category: {}", request.getName());

        // Check if slug already exists
        if (categoryRepository.existsBySlug(request.getSlug())) {
            throw new ValidationException("Category with slug " + request.getSlug() + " already exists");
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category.setDescription(request.getDescription());
        category.setImageUrl(request.getImageUrl());
        category.setDisplayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0);

        // Set parent if provided
        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found"));
            category.setParent(parent);
        }

        Category savedCategory = categoryRepository.save(category);
        log.info("Category created successfully with ID: {}", savedCategory.getId());

        return CategoryDTO.fromEntity(savedCategory);
    }

    @Cacheable(value = "categories", key = "#id")
    public CategoryDTO getCategoryById(UUID id) {
        log.info("Fetching category with ID: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
        return CategoryDTO.fromEntity(category);
    }

    public CategoryDTO getCategoryBySlug(String slug) {
        log.info("Fetching category with slug: {}", slug);
        Category category = categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with slug: " + slug));
        return CategoryDTO.fromEntity(category);
    }

    public List<CategoryDTO> getAllCategories() {
        log.info("Fetching all categories");
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::fromEntity)
                .toList();
    }

    public List<CategoryDTO> getRootCategories() {
        log.info("Fetching root categories");
        return categoryRepository.findRootCategories().stream()
                .map(CategoryDTO::fromEntity)
                .toList();
    }

    public List<CategoryDTO> getActiveCategories() {
        log.info("Fetching active categories");
        return categoryRepository.findActiveCategories().stream()
                .map(CategoryDTO::fromEntity)
                .toList();
    }

    @Transactional
    @CacheEvict(value = "categories", key = "#id")
    public void deleteCategory(UUID id) {
        log.info("Deleting category with ID: {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));

        // Soft delete
        category.setDeleted(true);
        category.setActive(false);
        categoryRepository.save(category);

        categoryRepository.delete(category);

        log.info("Category deleted successfully with ID: {}", id);
    }

}
