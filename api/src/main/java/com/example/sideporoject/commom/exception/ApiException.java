package com.example.sideporoject.commom.exception;

import com.example.sideporoject.commom.error.ErrorCode;
import com.example.sideporoject.commom.error.ErrorCodeIfs;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private final ErrorCodeIfs errorCodeIfs;

    public ApiException(ErrorCodeIfs errorCodeIfs) {
        this.errorCodeIfs = errorCodeIfs;
    }

    public ApiException( ErrorCodeIfs errorCodeIfs,String message) {
        super(message);
        this.errorCodeIfs = errorCodeIfs;
    }

    public ApiException(String message, Throwable cause, ErrorCodeIfs errorCodeIfs) {
        super(message, cause);
        this.errorCodeIfs = errorCodeIfs;
    }

    public ApiException(Throwable cause, ErrorCodeIfs errorCodeIfs) {
        super(cause);
        this.errorCodeIfs = errorCodeIfs;
    }
}
