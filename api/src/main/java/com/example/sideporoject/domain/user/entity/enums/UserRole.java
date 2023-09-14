package com.example.sideporoject.domain.user.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {

    MASTER("마스터"),
    MANAGER("매니저"),
    USER("일반 유저"),

    ;

    private final String description;
}
