package com.example.sideporoject.domain.token.service;

import com.example.sideporoject.commom.error.ErrorCode;
import com.example.sideporoject.commom.exception.ApiException;
import com.example.sideporoject.domain.auth.UserDetailsServiceImpl;
import com.example.sideporoject.domain.token.dto.TokenDto;
import com.example.sideporoject.domain.token.tokenhelper.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelper tokenHelper;

    public TokenDto issueAccessToken(String userEmail) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("userEmail", userEmail);
        return tokenHelper.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(String userEmail) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("userEmail", userEmail);
        return tokenHelper.issueRefreshToken(data);
    }

    public String validationToken(String token){

        Map<String, Object> data = tokenHelper.validationTokenWithThrow(token);
        Object userEmail = data.get("userEmail");
        Objects.requireNonNull(userEmail, () -> {
            throw new ApiException(ErrorCode.NULL_POINT);
        });
        return userEmail.toString();
    }

}
