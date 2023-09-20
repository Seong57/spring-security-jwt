package com.example.sideporoject.domain.token.service;


import com.example.sideporoject.commom.error.TokenErrorCode;
import com.example.sideporoject.commom.error.UserErrorCode;
import com.example.sideporoject.commom.exception.ApiException;
import com.example.sideporoject.domain.token.model.RefreshTokenDto;
import com.example.sideporoject.domain.token.entity.RefreshToken;
import com.example.sideporoject.domain.token.repository.RefreshTokenRepository;
import com.example.sideporoject.domain.user.entity.UserEntity;
import com.example.sideporoject.domain.user.repository.UserRepository;
import com.example.sideporoject.security.token.dto.TokenDto;
import com.example.sideporoject.domain.token.model.TokenResponse;
import com.example.sideporoject.security.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenService tokenService;
    private final UserRepository userRepository;


    public void deleteRefreshToken(RefreshTokenDto refreshTokenDto) {

        refreshTokenRepository.findByValue(refreshTokenDto.getRefreshToken())
                .ifPresent(refreshTokenRepository::delete);
    }

    public TokenResponse reissueAccessToken(RefreshTokenDto refreshTokenDto) {
        RefreshToken refreshToken = refreshTokenRepository.findByValue(refreshTokenDto.getRefreshToken())
                .orElseThrow(() -> new ApiException(TokenErrorCode.INVALID_TOKEN));

        String userEmail = tokenService.validationTokenWithUserEmail(refreshToken.getValue());

        UserEntity userEntity = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));

        Long userId = userEntity.getId();

        TokenDto accessToken = tokenService.issueAccessToken(userId);

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getValue())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();

    }
}
