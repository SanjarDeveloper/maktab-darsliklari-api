package com.example.maktabdarsliklariapp.entity.enums;

public enum RoleEnum {
    ROLE_USER("user"), //tengkuchli == RolEnum enum = new RoleEnum("Role USER")
    ROLE_ADMIN("admin"),
    ROLE_MODERATOR("moderator");
    private String description;

    RoleEnum(String description) {
        this.description = description;
    }
}
