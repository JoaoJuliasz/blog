package com.juliasz.blog.enums;

public enum UserStatus {
    ACTIVE,
    INACTIVE,
    PENDING;

    public static UserStatus fromString(String status) {
        return UserStatus.valueOf(status.toUpperCase());
    }
}
