package com.example.sideporoject.security.token.tokenhelper;

import com.example.sideporoject.security.token.dto.TokenDto;

import java.util.Map;

public interface TokenHelper {

    TokenDto issueAccessToken(Map<String, Object> data);

    TokenDto issueRefreshToken(Map<String, Object> data);

    Map<String, Object> validationTokenWithThrow(String token);
}
