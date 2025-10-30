package com.social.common.util;

import com.social.common.exception.ValidationException;

import java.util.regex.Pattern;

public class ValidationUtils {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+?[1-9]\\d{1,14}$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    public static void validateEmail(String email) {
        if (StringUtils.isEmpty(email))
            throw new ValidationException("Email cannot be empty");
        if (!EMAIL_PATTERN.matcher(email).matches())
            throw new ValidationException("Invalid email format");
    }

    public static void validatePhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            throw new ValidationException("Phone number cannot be empty");
        }
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new ValidationException("Invalid phone number format");
        }
    }

    public static void validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            throw new ValidationException("Password cannot be empty");
        }
        if (password.length() < 8) {
            throw new ValidationException("Password must be at least 8 characters long");
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new ValidationException(
                    "Password must contain at least one digit, one lowercase, " +
                            "one uppercase letter, and one special character"
            );
        }
    }

    public static void validateNotNull(Object object, String fieldName) {
        if (object == null)
            throw new ValidationException(fieldName + " cannot be null");
    }

    public static void validateNotEmpty(String value, String fieldName) {
        if (StringUtils.isEmpty(value)) {
            throw new ValidationException(fieldName + " cannot be empty");
        }
    }

}