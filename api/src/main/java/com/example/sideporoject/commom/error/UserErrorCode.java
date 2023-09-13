package com.example.sideporoject.commom.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs{

    USER_NOT_FOUND(1400, 1400, "유저를 찾을 수 없음")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String errorDescription;
}
