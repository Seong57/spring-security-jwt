package com.example.sideporoject.commom.exhandler;

import com.example.sideporoject.commom.costomresponse.reponse.Response;
import com.example.sideporoject.commom.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response<?>> globalExHandler(
            Exception e
    ){
        log.error("", e);

        return ResponseEntity.status(500)
                .body(Response.ERROR(ErrorCode.SERVER_ERROR));
    }


}
