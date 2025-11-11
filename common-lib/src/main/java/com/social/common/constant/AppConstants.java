package com.social.common.constant;

public class AppConstants {

    // Application
    public static final String APP_NAME = "Social Commerce Platform";
    public static final String APP_VERSION = "1.0.0";

    // Pagination
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;
    public static final String DEFAULT_SORT_BY = "createdAt";
    public static final String DEFAULT_SORT_DIRECTION = "DESC";

    // Date Format
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_ZONE = "UTC";

    // JWT
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_PREFIX = "Bearer ";
    public static final long JWT_EXPIRATION = 86400000L; // 24 hours
    public static final long JWT_REFRESH_EXPIRATION = 604800000L; // 7 days

    // Cache
    public static final String CACHE_USER = "users";
    public static final String CACHE_PRODUCT = "products";
    public static final String CACHE_ORDER = "orders";
    public static final int CACHE_TTL_MINUTES = 60;

    // Kafka Topics
    public static final String TOPIC_USER_EVENTS = "user.events";
    public static final String TOPIC_PRODUCT_EVENTS = "product.events";
    public static final String TOPIC_ORDER_EVENTS = "order.events";
    public static final String TOPIC_PAYMENT_EVENTS = "payment.events";
    public static final String TOPIC_NOTIFICATION_EVENTS = "notification.events";

    // Rate Limiting
    public static final int RATE_LIMIT_PER_MINUTE = 60;
    public static final int RATE_LIMIT_PER_HOUR = 1000;

    // File Upload
    public static final long MAX_FILE_SIZE = 10485760L; // 10MB
    public static final String[] ALLOWED_IMAGE_TYPES = {"image/jpeg", "image/png", "image/gif"};

    // Time Constants
    public static final long ONE_MINUTE = 60000L;
    public static final long ONE_HOUR = 3600000L;
    public static final long TWELVE_HOURS = 43200000L;
    public static final long ONE_DAY = 86400000L;
    public static final long ONE_WEEK = 604800000L;

    private AppConstants() {
        // Private constructor to prevent instantiation
    }

}







