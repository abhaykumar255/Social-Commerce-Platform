package com.social.gateway.filter;

import com.social.common.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtUtil jwtUtil;
    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    private static final String BLACKLIST_PREFIX = "token:blacklist:";

    private static final List<String> OPEN_ENDPOINTS = List.of(
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/auth/refresh",
            "/api/v1/auth/validate",
//            "/api/v1/users",
            "/actuator/health",
            "/swagger-ui",
            "/v3/api-docs"
    );


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();

            // Skip authentication for open endpoints
            if (isOpenEndpoint(path)) {
                return chain.filter(exchange);
            }

            // Check if Authorization header exists
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "Missing authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "Invalid authorization header", HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.substring(7);

            try {
                // Check if token is blacklisted (reactive)
                return isTokenBlacklisted(token)
                        .flatMap(isBlacklisted -> {
                            if (Boolean.TRUE.equals(isBlacklisted)) {
                                log.warn("Attempted to use blacklisted token");
                                return onError(exchange, "Token has been invalidated", HttpStatus.UNAUTHORIZED);
                            }

                            // Validate token
                            if (!jwtUtil.validateToken(token)) {
                                return onError(exchange, "Invalid or expired token", HttpStatus.UNAUTHORIZED);
                            }

                            // Extract user information and add to headers
                            String userId = jwtUtil.extractUserId(token).toString();
                            String email = jwtUtil.extractEmail(token);

                            // Add user info to request headers
                            ServerHttpRequest modifiedRequest = request.mutate()
                                    .header("X-User-Id", userId)
                                    .header("X-User-Email", email)
                                    .build();

                            return chain.filter(exchange.mutate().request(modifiedRequest).build());
                        })
                        .onErrorResume(e -> {
                            log.error("Error validating token: {}", e.getMessage());
                            return onError(exchange, "Authentication failed", HttpStatus.UNAUTHORIZED);
                        });

            } catch (Exception e) {
                log.error("Error processing authentication: {}", e.getMessage());
                return onError(exchange, "Authentication failed", HttpStatus.UNAUTHORIZED);
            }
        };
    }


    private boolean isOpenEndpoint(String path) {
        return OPEN_ENDPOINTS.stream().anyMatch(path::startsWith);
    }

    /**
     * Check if token is blacklisted in Redis (reactive)
     * @param token JWT token
     * @return Mono<Boolean> true if blacklisted, false otherwise
     */
    private Mono<Boolean> isTokenBlacklisted(String token) {
        String blacklistKey = BLACKLIST_PREFIX + token;
        return reactiveRedisTemplate.hasKey(blacklistKey)
                .defaultIfEmpty(false)
                .onErrorReturn(false); // If Redis is down, allow the request (fail open)
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        log.warn("Authentication error: {} - Status: {}", message, status);
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // Configuration properties if needed
    }
}
