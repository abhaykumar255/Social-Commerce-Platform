package com.social.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Users Service Routes
                .route("commerce-users-service", r -> r
                        .path("/api/v1/users/**", "/api/v1/auth/**")
                        .filters(f -> f
                                .stripPrefix(0)
                                .addRequestHeader("X-Gateway", "API-Gateway")
                                .circuitBreaker(config -> config
                                        .setName("usersServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/users-service")))
                        .uri("lb://commerce-users-service"))

                // Products Service Routes
                .route("commerce-products-service", r -> r
                        .path("/api/v1/products/**", "/api/v1/categories/**")
                        .filters(f -> f
                                .stripPrefix(0)
                                .addRequestHeader("X-Gateway", "API-Gateway")
                                .circuitBreaker(config -> config
                                        .setName("productsServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/products-service")))
                        .uri("lb://commerce-products-service"))

                // Orders Service Routes
                .route("commerce-orders-service", r -> r
                        .path("/api/v1/orders/**", "/api/v1/cart/**")
                        .filters(f -> f
                                .stripPrefix(0)
                                .addRequestHeader("X-Gateway", "API-Gateway")
                                .circuitBreaker(config -> config
                                        .setName("ordersServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/orders-service")))
                        .uri("lb://commerce-orders-service"))

                // Payments Service Routes
                .route("commerce-payments-service", r -> r
                        .path("/api/v1/payments/**")
                        .filters(f -> f
                                .stripPrefix(0)
                                .addRequestHeader("X-Gateway", "API-Gateway")
                                .circuitBreaker(config -> config
                                        .setName("paymentsServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/payments-service")))
                        .uri("lb://commerce-payments-service"))

                // Social Service Routes
                .route("commerce-social-service", r -> r
                        .path("/api/v1/posts/**", "/api/v1/comments/**", "/api/v1/follows/**")
                        .filters(f -> f
                                .stripPrefix(0)
                                .addRequestHeader("X-Gateway", "API-Gateway")
                                .circuitBreaker(config -> config
                                        .setName("socialServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/social-service")))
                        .uri("lb://commerce-social-service"))

                // Notifications Service Routes
                .route("commerce-notifications-service", r -> r
                        .path("/api/v1/notifications/**")
                        .filters(f -> f
                                .stripPrefix(0)
                                .addRequestHeader("X-Gateway", "API-Gateway")
                                .circuitBreaker(config -> config
                                        .setName("notificationsServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/notifications-service")))
                        .uri("lb://commerce-notifications-service"))

                .build();
    }
}
