package com.social.order.repository;

import com.social.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findByOrderNumber(String orderNumber);

    Page<Order> findByUserIdAndDeletedFalse(UUID userId, Pageable pageable);

    Page<Order> findByStatusAndDeletedFalse(Order.OrderStatus status, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.deleted = false AND o.userId = :userId AND o.status = :status")
    Page<Order> findByUserIdAndStatus(@Param("userId") UUID userId,
                                      @Param("status") Order.OrderStatus status,
                                      Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.deleted = false AND " +
            "o.createdAt BETWEEN :startDate AND :endDate")
    List<Order> findOrdersBetweenDates(@Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.deleted = false AND o.userId = :userId")
    long countByUserId(@Param("userId") UUID userId);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.deleted = false AND o.status = :status")
    long countByStatus(@Param("status") Order.OrderStatus status);

    @Query("SELECT SUM(o.total) FROM Order o WHERE o.deleted = false AND " +
            "o.status IN ('CONFIRMED', 'PROCESSING', 'SHIPPED', 'DELIVERED')")
    BigDecimal getTotalRevenue();
}
