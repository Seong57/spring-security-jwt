package com.example.sideporoject.commom.costomresponse.reponse;

import com.example.sideporoject.commom.error.ErrorCode;
import com.example.sideporoject.commom.error.ErrorCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    private Integer resultCode;
    private String resultMessage;
    private String resultDescription;

    public static Result OK() {
        return Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode())
                .resultMessage(ErrorCode.OK.getErrorDescription())
                .resultDescription("성공")
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getErrorDescription())
                .resultDescription("에러 발생")
                .build()
                ;
    }


    // validation용 ERROR
    public static Result ERROR(
            Integer errorCode,
            String errorDescription
    ){
        return Result.builder()
                .resultCode(errorCode)
                .resultMessage(errorDescription)
                .resultDescription("잘못된 요청입니다.")
                .build()
                ;
    }
}
