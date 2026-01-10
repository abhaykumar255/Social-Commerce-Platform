package com.social.order.service;

import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.order.client.ProductClient;
import com.social.order.dto.AddToCartRequest;
import com.social.order.dto.CartDTO;
import com.social.order.dto.UpdateCartItemRequest;
import com.social.order.model.Cart;
import com.social.order.model.CartItem;
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
    public CartDTO addToCart(UUID userId, AddToCartRequest request) {

        log.info("Adding product {} to cart from user {}", request.getProductId(), userId);

        // Getting or creating cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    return cartRepository.save(newCart);
                });

        // Getting product details
        ProductClient.ProductDTO product = productClient.getProductById(request.getProductId()).getData();

        if (!product.inStock || product.availableQuantity < request.getQuantity())
            throw new ValidationException("Product is out of stock");

        // Check if item already exists in cart
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), request.getProductId())
                .orElse(null);

        if (cartItem != null) {
            // Update quantity
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            cartItem.calculateSubtotal();
        } else {
            // Create new cart item
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProductId(product.id);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setUnitPrice(product.discountPrice != null ? product.discountPrice : product.price);
            cartItem.calculateSubtotal();
            cart.getItems().add(cartItem);
        }

        // Calculate cart total
        cart.calculateTotal();
        Cart savedCart = cartRepository.save(cart);

        log.info("Product added to cart successfully");

        return CartDTO.fromEntity(savedCart);
    }

    public CartDTO getCart(UUID userId){
        log.info("Fetching cart for user: {}", userId);
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        return CartDTO.fromEntity(cart);
    }

    @Transactional
    public CartDTO updateCartItem(UUID userId, UUID itemId, UpdateCartItemRequest request){
        log.info("Updating cart item {} for user {}", itemId, userId);

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        // Validating Stocks
        ProductClient.ProductDTO product = productClient.getProductById(cartItem.getProductId()).getData();
        if (!product.inStock || product.availableQuantity < request.getQuantity()) {
            throw new ValidationException("Insufficient stock");
        }

        cartItem.setQuantity(request.getQuantity());
        cartItem.calculateSubtotal();

        cart.calculateTotal();
        Cart savedCart = cartRepository.save(cart);

        log.info("Cart item updated successfully");
        return CartDTO.fromEntity(savedCart);
    }

    @Transactional
    public CartDTO removeCartItem(UUID userId, UUID itemId){
        log.info("Removing item {} from cart for user {}", itemId, userId);

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cart.getItems().removeIf(
                item -> item.getId().equals(itemId)
        );

        cart.calculateTotal();
        Cart savedCart = cartRepository.save(cart);

        log.info("Cart item removed successfully");
        return CartDTO.fromEntity(savedCart);
    }

    @Transactional
    public void clearCart(UUID  userId){
        log.info("Clearing cart for user: {}", userId);

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cart.getItems().clear();
        cart.calculateTotal();
        cartRepository.save(cart);

        log.info("Cart cleared successfully");
    }


    private CartDTO addToCartFallback(UUID userId, AddToCartRequest request, Exception e) {
        log.error("Failed to add to cart: {}", e.getMessage());
        throw new ValidationException("Cart service is temporarily unavailable");
    }
}
