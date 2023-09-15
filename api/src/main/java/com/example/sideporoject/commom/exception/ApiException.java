package com.example.sideporoject.commom.exception;

import com.example.sideporoject.commom.error.ErrorCode;
import com.example.sideporoject.commom.error.ErrorCodeIfs;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private final ErrorCodeIfs errorCodeIfs;
    private final String errorDescription;

    public ApiException(ErrorCodeIfs errorCodeIfs){
        super(errorCodeIfs.getErrorDescription());
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getErrorDescription();
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, String errorDescription) {
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }

    public ApiException(String message, ErrorCodeIfs errorCodeIfs, String errorDescription) {
        super(message);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }

    public ApiException(String message, Throwable cause, ErrorCodeIfs errorCodeIfs, String errorDescription) {
        super(message, cause);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }

    public ApiException(Throwable cause, ErrorCodeIfs errorCodeIfs, String errorDescription) {
        super(cause);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCodeIfs errorCodeIfs, String errorDescription) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }
}
