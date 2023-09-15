package com.example.sideporoject.domain.user.controller;

import com.example.sideporoject.commom.costomresponse.reponse.Response;
import com.example.sideporoject.domain.auth.UserDetailsImpl;
import com.example.sideporoject.domain.health.HealthResponse;
import com.example.sideporoject.domain.user.HealthCall;
import com.example.sideporoject.domain.user.controller.model.UserResponse;
import com.example.sideporoject.domain.user.entity.UserEntity;
import com.example.sideporoject.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @GetMapping("/me")
    public Response<UserResponse> me(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            UserDetailsImpl userDetails
    ){

        UserResponse response = UserResponse.builder()
                .id(userDetails.getId())
                .name(userDetails.getUsername())
                .email(userDetails.getEmail())
                .build();

        return Response.OK(response);

    }
    @GetMapping("/test")
    public Response<HealthResponse> test(){

        HealthResponse health = HealthResponse.builder()
                .id(1L)
                .name("health")
                .registeredAt(LocalDateTime.now())
                .build();

        return Response.OK(health);
    }
}
