package com.example.sideporoject.domain.user.service.impl;

import com.example.sideporoject.commom.error.ErrorCode;
import com.example.sideporoject.commom.error.UserErrorCode;
import com.example.sideporoject.commom.exception.ApiException;
import com.example.sideporoject.domain.token.dto.TokenDto;
import com.example.sideporoject.domain.token.model.TokenResponse;
import com.example.sideporoject.domain.token.service.TokenService;
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
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final UserConverter userConverter;

    @Override
    public UserResponse save(UserRegisterRequest request) {

        boolean b = userRepository.existsByEmail(request.getEmail());
        if (b) {
            throw new ApiException(UserErrorCode.EXISTS_USER, "이미 존재하는 회원입니다.");
        }

        UserEntity userEntity = userConverter.toEntity(request);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRegisteredAt(LocalDateTime.now());
        userEntity.setStatus(UserStatus.REGISTERED);
        userEntity.setRole(UserRole.USER);

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
    public TokenResponse login(UserLoginRequest request) {


        UserEntity userEntity = userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                request.getEmail(), request.getPassword(), UserStatus.REGISTERED
        ).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

        TokenDto tokenDto = tokenService.issueAccessToken(userEntity.getId());

        return null;
    }


    // userDetailsService 에서 사용될 메소드
    @Override
    public Optional<UserEntity> findByEmailWithThrow(String email) {
        return userRepository.findByEmail(email);
    }
}
