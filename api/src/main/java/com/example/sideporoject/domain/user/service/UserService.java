package com.example.sideporoject.domain.user.service;

import com.example.sideporoject.domain.user.controller.model.UserRegisterRequest;
import com.example.sideporoject.domain.user.entity.UserEntity;

public interface UserService {

    UserEntity save(UserRegisterRequest request);


}
