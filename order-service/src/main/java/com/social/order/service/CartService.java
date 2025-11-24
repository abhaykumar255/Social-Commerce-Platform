package com.social.order.service;

import com.social.common.exception.ValidationException;
import com.social.order.client.ProductClient;
import com.social.order.dto.AddToCartRequest;
import com.social.order.dto.CartDTO;
import com.social.order.repository.CartItemRepository;
import com.social.order.repository.CartRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductClient productClient;

    @Transactional
    @CircuitBreaker(name = "cartService", fallbackMethod = "addToCartFallback")
    public CartDTO addToCart(UUID userId, AddToCartRequest request){

        log.info("Adding product {} to cart from user {}", request.getProductId(), userId);

        // Getting or creating cart


        return null;
    }


    private CartDTO addToCartFallback(UUID userId, AddToCartRequest request, Exception e){
        log.error("Failed to add to cart: {}", e.getMessage());
        throw new ValidationException("Cart service is temporarily unavailable");
    }
}
