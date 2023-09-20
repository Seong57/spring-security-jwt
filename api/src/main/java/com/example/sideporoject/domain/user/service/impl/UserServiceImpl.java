package com.example.sideporoject.domain.user.service.impl;

import com.example.sideporoject.commom.error.ErrorCode;
import com.example.sideporoject.commom.error.UserErrorCode;
import com.example.sideporoject.commom.exception.ApiException;
import com.example.sideporoject.domain.token.entity.RefreshToken;
import com.example.sideporoject.domain.token.repository.RefreshTokenRepository;
import com.example.sideporoject.security.token.converter.TokenConverter;
import com.example.sideporoject.security.token.dto.TokenDto;
import com.example.sideporoject.domain.token.model.TokenResponse;
import com.example.sideporoject.security.token.service.TokenService;
import com.example.sideporoject.domain.user.controller.model.UserLoginRequest;
import com.example.sideporoject.domain.user.controller.model.UserRegisterRequest;
import com.example.sideporoject.domain.user.controller.model.UserResponse;
import com.example.sideporoject.domain.user.converter.UserConverter;
import com.example.sideporoject.domain.user.entity.UserEntity;
import com.example.sideporoject.domain.user.entity.enums.UserRole;
import com.example.sideporoject.domain.user.entity.enums.UserStatus;
import com.example.sideporoject.domain.user.repository.UserRepository;
import com.example.sideporoject.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final TokenConverter tokenConverter;
    private final UserConverter userConverter;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public UserResponse save(UserRegisterRequest request) {

        boolean existsByEmail = userRepository.existsByEmail(request.getEmail());
        if (existsByEmail) {
            throw new ApiException(UserErrorCode.EXISTS_USER, "이미 존재하는 회원입니다.");
        }

        UserEntity userEntity = userConverter.toEntity(request);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRegisteredAt(LocalDateTime.now());
        userEntity.setStatus(UserStatus.REGISTERED);
        userEntity.setRole(UserRole.ROLE_USER);

        userRepository.save(userEntity);
        return userConverter.toResponse(userEntity);
    }

    @Override
    public UserResponse findByIdWithThrow(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

        UserResponse response = userConverter.toResponse(userEntity);
        return response;
    }

    @Override
    public TokenResponse  login(UserLoginRequest request) {

        UserEntity userEntity = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND, "유저를 찾을 수 없습니다"));
        System.out.println(userEntity.getPassword());

        if (passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            TokenDto token = tokenService.issueAccessToken(userEntity.getId());
            System.out.println(userEntity.getEmail());
            TokenDto issueRefreshToken = tokenService.issueRefreshToken(userEntity.getId());
            TokenResponse response = tokenConverter.toResponse(token, issueRefreshToken);

            // refresh token을 db에 저장한다. (성능 이슈로 인해 원래는 redis같은 인메모리 저장소에 저장해야함)
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setUserId(userEntity.getId());
            refreshToken.setValue(issueRefreshToken.getToken());
            refreshToken.setExpiredAt(issueRefreshToken.getExpiredAt());
            refreshTokenRepository.save(refreshToken);


            return response;
        } else {
            throw new ApiException(ErrorCode.BAD_REQUEST, "이메일과 비밀번호를 확인해주세요");
        }
    }


    // userDetailsService 에서 사용될 메소드
    @Override
    public Optional<UserEntity> findByEmailWithThrow(String email) {
        return userRepository.findByEmail(email);
    }
}
