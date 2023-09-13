package com.example.sideporoject.commom.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeIfs{

    OK(200, 200, "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버 내 오류"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512, "null point"),
    ;
    private final Integer httpStatusCode;   // 클라이언트에 보여줄 status code
    private final Integer errorCode;    // 우리 서버 내부에서 사용할 에러 코드
    private final String errorDescription;  // 에러 설명
}
