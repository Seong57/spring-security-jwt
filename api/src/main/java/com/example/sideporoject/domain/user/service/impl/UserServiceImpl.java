package com.example.sideporoject.domain.user.service.impl;

import com.example.sideporoject.domain.user.controller.model.UserRegisterRequest;
import com.example.sideporoject.domain.user.entity.UserEntity;
import com.example.sideporoject.domain.user.entity.enums.UserStatus;
import com.example.sideporoject.domain.user.repository.UserRepository;
import com.example.sideporoject.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserEntity save(UserRegisterRequest request) {
        UserEntity userEntity = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .address(request.getAddress())
                .build();

        userEntity.setRegisteredAt(LocalDateTime.now());
        userEntity.setStatus(UserStatus.REGISTERED);

        return userRepository.save(userEntity);
    }
}
