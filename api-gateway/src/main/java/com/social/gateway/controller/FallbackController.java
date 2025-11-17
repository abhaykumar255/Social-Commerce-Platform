package com.social.gateway.controller;

import com.social.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/user-service")
    public ResponseEntity<ApiResponse<String>> userServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("User service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/product-service")
    public ResponseEntity<ApiResponse<String>> productServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Product service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/order-service")
    public ResponseEntity<ApiResponse<String>> orderServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Order service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/payment-service")
    public ResponseEntity<ApiResponse<String>> paymentServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Payment service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/social-service")
    public ResponseEntity<ApiResponse<String>> socialServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Social service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/notification-service")
    public ResponseEntity<ApiResponse<String>> notificationServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Notification service is temporarily unavailable. Please try again later."));
    }
}
