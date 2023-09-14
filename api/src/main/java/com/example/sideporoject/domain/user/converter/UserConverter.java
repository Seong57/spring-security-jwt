package com.example.sideporoject.domain.user.converter;

import com.example.sideporoject.commom.error.ErrorCode;
import com.example.sideporoject.commom.exception.ApiException;
import com.example.sideporoject.domain.user.controller.model.UserRegisterRequest;
import com.example.sideporoject.domain.user.controller.model.UserResponse;
import com.example.sideporoject.domain.user.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserConverter {

    public UserResponse toResponse(UserEntity request) {
        return Optional.ofNullable(request)
            .map(it -> {
                return UserResponse.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .build();
            })
            .orElseThrow();

    }

    public UserEntity toEntity(UserRegisterRequest request) {

        return Optional.ofNullable(request)
                .map(it -> {
                    return UserEntity.builder()
                            .name(it.getName())
                            .email(it.getEmail())
                            .password(it.getPassword())
                            .address(it.getAddress())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "userRegisterRequest is null"));
    }


}
