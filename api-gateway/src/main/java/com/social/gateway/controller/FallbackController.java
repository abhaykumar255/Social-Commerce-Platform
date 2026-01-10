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

    @GetMapping("/users-service")
    public ResponseEntity<ApiResponse<String>> usersServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Users service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/products-service")
    public ResponseEntity<ApiResponse<String>> productsServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Products service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/orders-service")
    public ResponseEntity<ApiResponse<String>> ordersServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Orders service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/payments-service")
    public ResponseEntity<ApiResponse<String>> paymentsServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Payments service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/social-service")
    public ResponseEntity<ApiResponse<String>> socialServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Social service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/notifications-service")
    public ResponseEntity<ApiResponse<String>> notificationsServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Notifications service is temporarily unavailable. Please try again later."));
    }
}
