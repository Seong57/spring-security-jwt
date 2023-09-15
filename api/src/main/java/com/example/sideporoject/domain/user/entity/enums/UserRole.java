package com.example.sideporoject.domain.user.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {

    ROLE_MASTER("마스터"),
    ROLE_MANAGER("매니저"),
    ROLE_USER("일반 유저"),

    ;

    private final String description;
}
