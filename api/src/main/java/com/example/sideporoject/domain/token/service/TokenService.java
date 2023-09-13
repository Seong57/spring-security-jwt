package com.example.sideporoject.domain.token.service;

import com.example.sideporoject.commom.error.ErrorCode;
import com.example.sideporoject.commom.exception.ApiException;
import com.example.sideporoject.domain.token.dto.TokenDto;
import com.example.sideporoject.domain.token.tokenhelper.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelper tokenHelper;

    public TokenDto issueAccessToken(Long userId) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        return tokenHelper.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        return tokenHelper.issueRefreshToken(data);
    }

    public Long validationToken(String token){

        Map<String, Object> data = tokenHelper.validationTokenWithThrow(token);
        Object userId = data.get("userId");
        Objects.requireNonNull(userId, () -> {
            throw new ApiException(ErrorCode.NULL_POINT);
        });
        return Long.parseLong(userId.toString());
    }
}
