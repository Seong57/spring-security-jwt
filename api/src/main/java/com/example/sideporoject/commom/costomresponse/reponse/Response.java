package com.example.sideporoject.commom.costomresponse.reponse;

import com.example.sideporoject.commom.error.ErrorCodeIfs;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Response<T> {

    private Result result;
    @Valid
    private T data;

    public static <T> Response<T> OK(T result){
        Response<T> response = new Response<>();
        response.result = Result.OK();
        response.data = result;
        return response;
    }

    public static Response<Object> ERROR(Result result) {
        Response<Object> response = new Response<>();
//        response.resultCode 는 exHandeler에서 설정해줌
        response.result = result;
        return response;
    }

    public static Response<Object> ERROR(ErrorCodeIfs errorCodeIfs) {
        Response<Object> response = new Response<>();
        response.result = Result.ERROR(errorCodeIfs);
        return response;
    }

    public static Response<Object> ERROR(Integer errorCode, String errorDescription) {
        Response<Object> response = new Response<>();
        response.result = Result.ERROR(errorCode, errorDescription);
        return response;
    }


}
