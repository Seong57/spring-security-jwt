package com.example.sideporoject.domain.user.service;

import com.example.sideporoject.domain.user.controller.model.UserLoginRequest;
import com.example.sideporoject.domain.user.controller.model.UserRegisterRequest;
import com.example.sideporoject.domain.user.entity.UserEntity;

public interface UserService {

    UserEntity save(UserRegisterRequest request);

    UserEntity findById(Long id);


    String login(UserLoginRequest request);
}
