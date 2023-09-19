package com.example.sideporoject.security.token.service;

import com.example.sideporoject.commom.error.ErrorCode;
import com.example.sideporoject.commom.error.UserErrorCode;
import com.example.sideporoject.commom.exception.ApiException;
import com.example.sideporoject.domain.user.controller.model.UserResponse;
import com.example.sideporoject.domain.user.entity.UserEntity;
import com.example.sideporoject.domain.user.repository.UserRepository;
import com.example.sideporoject.domain.user.service.UserService;
import com.example.sideporoject.security.token.dto.TokenDto;
import com.example.sideporoject.security.token.tokenhelper.TokenHelper;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelper tokenHelper;
    private final UserRepository userRepository;

    public TokenDto issueAccessToken(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));

        HashMap<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        data.put("userEmail", userEntity.getEmail());
        data.put("role", userEntity.getRole());
        return tokenHelper.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));

        HashMap<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        data.put("userEmail", userEntity.getEmail());
        data.put("role", userEntity.getRole());
        return tokenHelper.issueRefreshToken(data);
    }

    public String validationTokenWithUserEmail(String token){

        Map<String, Object> data = tokenHelper.validationTokenWithThrow(token);
        Object userEmail = data.get("userEmail");
        Objects.requireNonNull(userEmail, () -> {
            throw new ApiException(ErrorCode.NULL_POINT);
        });
        return userEmail.toString();
    }
}
