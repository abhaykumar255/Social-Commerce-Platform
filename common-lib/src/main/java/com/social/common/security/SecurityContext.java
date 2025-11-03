package com.social.common.security;


import java.util.UUID;

public class SecurityContext {

    private static final ThreadLocal<UUID> currentUserId = new ThreadLocal<>();
    private static final ThreadLocal<String> currentUserEmail = new ThreadLocal<>();

    public static void setCurrentUserId(UUID userId) {
        currentUserId.set(userId);
    }

    public static UUID getCurrentUserId() {
        return currentUserId.get();
    }

    public static void setCurrentUserEmail(String email) {
        currentUserEmail.set(email);
    }

    public static String getCurrentUserEmail() {
        return currentUserEmail.get();
    }

    public static void clear() {
        currentUserId.remove();
        currentUserEmail.remove();
    }

}