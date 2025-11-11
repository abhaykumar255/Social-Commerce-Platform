package com.social.user.service;

import com.social.common.exception.UnauthorizedException;
import com.social.common.exception.ValidationException;
import com.social.common.security.JwtUtil;
import com.social.common.security.PasswordEncoder;
import com.social.user.dto.LoginRequest;
import com.social.user.dto.LoginResponse;
import com.social.user.model.User;
import com.social.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.social.common.constant.AppConstants.ONE_DAY;
import static com.social.common.constant.StringConstants.TOKEN_TYPE_BEARER;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final RedisTemplate<String, String> redisTemplate;

    private static final String BLACKLIST_PREFIX = "token:blacklist:";
    private static final String ACTIVE_SESSION_PREFIX = "session:active:";

    @Transactional
    public LoginResponse login(LoginRequest request) {
        log.info("Login attempt for email: {}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        if (user.getLockedUntil() != null && user.getLockedUntil().isAfter(LocalDateTime.now()))
            throw new UnauthorizedException("Account is locked. Please try again later.");

        if (user.getStatus() != User.UserStatus.ACTIVE)
            throw new UnauthorizedException("Account is not active. Please contact support.");

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            userService.incrementFailedLoginAttempts(request.getEmail());
            throw new UnauthorizedException("Invalid email or password");
        }

        // Resetting failed login attempts on successful login
        userService.resetFailedLoginAttempts(user.getId());

        userService.updateLastLogin(user.getId());

        String token = jwtUtil.generateToken(user.getId(), user.getEmail());

        // Storing active session in Redis (for tracking and logout)
        storeActiveSession(user.getId(), token);

        log.info("Login successful for user: {}", user.getEmail());
        return LoginResponse.builder()
                .token(token)
                .tokenType(TOKEN_TYPE_BEARER)
                .expiresIn(ONE_DAY) // 24 hours
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();

    }

    public void logout(String token) {
        // Logout user by invalidating the token
        // Adds token to Redis blacklist and removes from active sessions
        try {
            UUID userId = jwtUtil.extractUserId(token);

            Date expiration = jwtUtil.extractExpiration(token);
            long ttl = expiration.getTime() - System.currentTimeMillis();

            if (ttl > 0) {
                // Adding token to the blacklist
                String blacklistKey = BLACKLIST_PREFIX + token;
                redisTemplate.opsForValue().set(
                        blacklistKey,
                        "blacklisted",
                        ttl,
                        TimeUnit.MILLISECONDS
                );

                // Removing from Active Sessions
                String sessionKey = ACTIVE_SESSION_PREFIX + userId;
                redisTemplate.opsForSet().remove(sessionKey, token);

                log.info("User {} logged out successfully. Token blacklisted.", userId);
            } else {
                log.warn("Attempted to logout with expired token for user: {}", userId);
            }

        } catch (Exception e) {
            log.error("Error during logout: {}", e.getMessage(), e);
            throw new ValidationException("Failed to logout. Please try again.");
        }
    }

    public void logoutAllDevices(UUID userId) {
        try {
            String sessionKey = ACTIVE_SESSION_PREFIX + userId;

            var activeTokens = redisTemplate.opsForSet().members(sessionKey);

            if (activeTokens != null && !activeTokens.isEmpty()) {
                for (String token : activeTokens) {
                    // Add each token to blacklist
                    Date expiration = jwtUtil.extractExpiration(token);
                    long ttl = expiration.getTime() - System.currentTimeMillis();

                    if (ttl > 0) {
                        String blacklistKey = BLACKLIST_PREFIX + token;
                        redisTemplate.opsForValue().set(
                                blacklistKey,
                                "blacklisted",
                                ttl,
                                TimeUnit.MILLISECONDS
                        );
                    }
                }

                // Remove all active sessions
                redisTemplate.delete(sessionKey);

                log.info("User {} logged out from all devices. {} tokens invalidated.",
                        userId, activeTokens.size());
            }
        } catch (Exception e) {
            log.error("Error during logout from all devices: {}", e.getMessage(), e);
            throw new ValidationException("Failed to logout from all devices. Please try again.");
        }
    }


    public boolean isTokenBlacklisted(String token) {
        String blackListKey = BLACKLIST_PREFIX + token;
        return redisTemplate.hasKey(blackListKey);
    }

    private void storeActiveSession(UUID userId, String token) {
        try {
            String sessionKey = ACTIVE_SESSION_PREFIX + userId;

            // Add token to user's active sessions set
            redisTemplate.opsForSet().add(sessionKey, token);

            redisTemplate.expire(sessionKey, 25, TimeUnit.HOURS);
            log.debug("Active session stored for user: {}", userId);
        } catch (Exception e) {
            log.error("Error storing active session: {}", e.getMessage(), e);
        }
    }

    // Get count of active sessions for a user
    public long getActiveSessionCount(UUID uuid) {
        String sessionKey = ACTIVE_SESSION_PREFIX + uuid;
        Long count = redisTemplate.opsForSet().size(sessionKey);

        return count != null ? count : 0;
    }


}
