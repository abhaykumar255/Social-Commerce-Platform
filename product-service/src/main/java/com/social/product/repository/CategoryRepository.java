package com.social.product.repository;

import com.social.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, UUID> {

    Optional<Category> findBySlug(String slug);

    boolean existsBySlug(String slug);

    @Query("SELECT c FROM Category c WHERE c.deleted = false AND c.parent IS NULL ORDER BY c.displayOrder")
    List<Category> findRootCategories();

    @Query("SELECT c FROM Category c WHERE c.deleted = false AND c.parent.id = :parentId ORDER BY c.displayOrder")
    List<Category> findByParentId(UUID parentId);

    @Query("SELECT c FROM Category c WHERE c.deleted = false AND c.active = true ORDER BY c.displayOrder")
    List<Category> findActiveCategories();
}
