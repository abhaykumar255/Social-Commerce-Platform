package com.social.common.constant;

public class StringConstants {

    public static final String EMPTY_STRING = "";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MODERATOR = "ROLE_MODERATOR";
    public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String ROLE_GUEST = "ROLE_GUEST";
    public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";

    public static final String TOKEN_TYPE_BEARER = "Bearer";
    public static final String TOKEN_TYPE_REFRESH = "Refresh";
    public static final String TOKEN_TYPE_RESET = "Reset";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // Permission Constants
    public static final String USER_READ = "USER_READ";
    public static final String USER_WRITE = "USER_WRITE";
    public static final String USER_DELETE = "USER_DELETE";
    public static final String USER_UPDATE = "USER_UPDATE";
    public static final String USER_CREATE = "USER_CREATE";
    public static final String PRODUCT_READ = "PRODUCT_READ";
    public static final String PRODUCT_WRITE = "PRODUCT_WRITE";
    public static final String PRODUCT_DELETE = "PRODUCT_DELETE";
    public static final String PRODUCT_UPDATE = "PRODUCT_UPDATE";
    public static final String ORDER_READ = "ORDER_READ";
    public static final String ORDER_WRITE = "ORDER_WRITE";
    public static final String ORDER_DELETE = "ORDER_DELETE";
    public static final String ORDER_UPDATE = "ORDER_UPDATE";

    private StringConstants() {
        // Private constructor to prevent instantiation
    }
}
