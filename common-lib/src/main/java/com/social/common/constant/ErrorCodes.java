package com.social.common.constant;

public class ErrorCodes {

    // General Errors
    public static final String INTERNAL_SERVER_ERROR = "ERR_001";
    public static final String VALIDATION_ERROR = "ERR_002";
    public static final String RESOURCE_NOT_FOUND = "ERR_003";
    public static final String UNAUTHORIZED = "ERR_004";
    public static final String FORBIDDEN = "ERR_005";
    public static final String BAD_REQUEST = "ERR_006";

    // User Errors
    public static final String USER_NOT_FOUND = "USER_001";
    public static final String USER_ALREADY_EXISTS = "USER_002";
    public static final String INVALID_CREDENTIALS = "USER_003";
    public static final String USER_INACTIVE = "USER_004";
    public static final String USER_LOCKED = "USER_005";

    // Product Errors
    public static final String PRODUCT_NOT_FOUND = "PROD_001";
    public static final String PRODUCT_OUT_OF_STOCK = "PROD_002";
    public static final String INVALID_PRODUCT_DATA = "PROD_003";

    // Order Errors
    public static final String ORDER_NOT_FOUND = "ORD_001";
    public static final String ORDER_ALREADY_PROCESSED = "ORD_002";
    public static final String ORDER_CANNOT_BE_CANCELLED = "ORD_003";
    public static final String INVALID_ORDER_STATUS = "ORD_004";

    // Payment Errors
    public static final String PAYMENT_FAILED = "PAY_001";
    public static final String PAYMENT_NOT_FOUND = "PAY_002";
    public static final String INSUFFICIENT_FUNDS = "PAY_003";
    public static final String PAYMENT_ALREADY_PROCESSED = "PAY_004";

    // Authentication Errors
    public static final String INVALID_TOKEN = "AUTH_001";
    public static final String TOKEN_EXPIRED = "AUTH_002";
    public static final String INVALID_REFRESH_TOKEN = "AUTH_003";

    private ErrorCodes() {
        // Private constructor to prevent instantiation
    }

}







