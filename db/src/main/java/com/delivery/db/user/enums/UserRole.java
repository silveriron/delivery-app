package com.delivery.db.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private final String value;
}