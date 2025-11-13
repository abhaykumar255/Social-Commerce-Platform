package com.social.user.controller;

import com.social.common.dto.ApiResponse;
import com.social.common.security.JwtUtil;
import com.social.user.dto.CreateUserRequest;
import com.social.user.dto.LoginRequest;
import com.social.user.dto.LoginResponse;
import com.social.user.dto.UserDTO;
import com.social.user.service.AuthService;
import com.social.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.social.common.constant.StringConstants.AUTHORIZATION_HEADER;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for authentication and session management")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Create a new user account")
    public ResponseEntity<ApiResponse<UserDTO>> register(@Valid @RequestBody CreateUserRequest request) {
        log.info("REST request to register user: {}", request.getEmail());
        UserDTO user = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(user, "User registered successfully"));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticate user and return JWT token")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        log.info("REST request to login user: {}", request.getEmail());
        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(ApiResponse.success(response, "Login successful"));
    }

    @PostMapping("/logout")
    @Operation(
            summary = "Logout user from current device",
            description = "Invalidate the current JWT token and remove from active sessions"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader(AUTHORIZATION_HEADER) String authHeader) {
        log.info("REST request to logout user");

        String token = extractToken(authHeader);

        authService.logout(token);
        return ResponseEntity.ok(ApiResponse.success(null, "Logout successful"));

    }

    @PostMapping("/logout-all")
    @Operation(
            summary = "Logout user from all devices",
            description = "Invalidate all JWT tokens for the user across all devices"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ApiResponse<Map<String, Object>>> logoutAll(@RequestHeader(AUTHORIZATION_HEADER) String authHeader) {
        log.info("REST request to logout user from all devices");

        String token = extractToken(authHeader);
        UUID userId = jwtUtil.extractUserId(token);

        long sessionCount = authService.getActiveSessionCount(userId);

        // logging out from all devices
        authService.logoutAllDevices(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("devicesLoggedOut", sessionCount);
        response.put("message", "Logged out from all devices successfully");

        return ResponseEntity.ok(ApiResponse.success(response, "Logged out from all devices"));
    }

    @GetMapping("/sessions/active")
    @Operation(
            summary = "Get active session count",
            description = "Returns the number of active sessions (devices) for the current user"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getActiveSessions(@RequestHeader(AUTHORIZATION_HEADER) String authHeader) {
        log.info("REST request to get active sessions");

        String token = extractToken(authHeader);
        UUID userId = jwtUtil.extractUserId(token);

        long sessionCount = authService.getActiveSessionCount(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("activeSessionsCount", sessionCount);

        return ResponseEntity.ok(ApiResponse.success(response, "Active sessions count retrieved successfully"));
    }

    @GetMapping("/validate")
    @Operation(
            summary = "Validate JWT token",
            description = "Check if the provided JWT token is valid and not blacklisted"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ApiResponse<Map<String, Object>>> validateToken(@RequestHeader(AUTHORIZATION_HEADER) String authHeader) {
        log.info("REST request to validate token");

        try {
            String token = extractToken(authHeader);

            // Checking if the token is blacklisted
            if (authService.isTokenBlacklisted(token)) {
                Map<String, Object> response = new HashMap<>();
                response.put("valid", false);
                response.put("reason", "Token has been invalidated");
                return ResponseEntity.ok(ApiResponse.success(response, "Token is invalid"));
            }

            boolean isValid = jwtUtil.validateToken(token);

            if (isValid) {
                UUID userId = jwtUtil.extractUserId(token);
                String email = jwtUtil.extractEmail(token);

                Map<String, Object> response = new HashMap<>();
                response.put("valid", true);
                response.put("userId", userId);
                response.put("email", email);

                return ResponseEntity.ok(ApiResponse.success(response, "Token is valid"));
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("valid", false);
                response.put("reason", "Token validation failed");
                return ResponseEntity.ok(ApiResponse.success(response, "Token is invalid"));
            }

        } catch (Exception e) {
            log.error("Error validating token: {}", e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("valid", false);
            response.put("reason", e.getMessage());
            return ResponseEntity.ok(ApiResponse.success(response, "Token is invalid"));
        }
    }

    private String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new IllegalArgumentException("Invalid Authorization header format");
    }

}
