package com.example.sideporoject.commom.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs{

    USER_NOT_FOUND(400, 1400, "유저를 찾을 수 없음"),
    EXISTS_USER(400, 1401, "이미 존재하는 회원입니다."),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String errorDescription;
}
