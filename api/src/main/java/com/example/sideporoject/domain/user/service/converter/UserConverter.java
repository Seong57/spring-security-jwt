package com.example.sideporoject.domain.user.service.converter;

import com.example.sideporoject.domain.user.controller.model.UserRegisterRequest;
import com.example.sideporoject.domain.user.controller.model.UserResponse;
import com.example.sideporoject.domain.user.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public UserResponse toResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }


}
