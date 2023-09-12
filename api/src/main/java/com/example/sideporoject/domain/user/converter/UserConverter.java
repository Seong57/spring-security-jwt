package com.example.sideporoject.domain.user.converter;

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


}
