package com.example.sideporoject.domain.user.service;

import com.example.sideporoject.domain.token.model.TokenResponse;
import com.example.sideporoject.domain.user.controller.model.UserLoginRequest;
import com.example.sideporoject.domain.user.controller.model.UserRegisterRequest;
import com.example.sideporoject.domain.user.controller.model.UserResponse;
import com.example.sideporoject.domain.user.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    UserResponse save(UserRegisterRequest request);

    UserResponse findByIdWithThrow(Long id);

    TokenResponse login(UserLoginRequest request);

    Optional<UserEntity> findByEmailWithThrow(String email);
}
