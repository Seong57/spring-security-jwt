package com.example.sideporoject.security.token.converter;

import com.example.sideporoject.commom.error.ErrorCode;
import com.example.sideporoject.commom.exception.ApiException;
import com.example.sideporoject.security.token.dto.TokenDto;
import com.example.sideporoject.domain.token.model.TokenResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TokenConverter {

    public TokenResponse toResponse(
            TokenDto accessToken,
            TokenDto refreshToken
    ){
        Objects.requireNonNull(accessToken, ()->{throw new ApiException(ErrorCode.NULL_POINT);});
        Objects.requireNonNull(refreshToken, ()->{throw new ApiException(ErrorCode.NULL_POINT);});

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
