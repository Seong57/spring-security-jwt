package com.example.sideporoject.domain.user.controller;

import com.example.sideporoject.commom.costomresponse.reponse.Response;
import com.example.sideporoject.domain.token.model.TokenResponse;
import com.example.sideporoject.domain.user.controller.model.UserLoginRequest;
import com.example.sideporoject.domain.user.controller.model.UserRegisterRequest;
import com.example.sideporoject.domain.user.controller.model.UserResponse;
import com.example.sideporoject.domain.user.entity.UserEntity;
import com.example.sideporoject.domain.user.service.UserService;
import com.example.sideporoject.domain.user.converter.UserConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open-api/user")
@RequiredArgsConstructor
public class UserOpenApiController {

    private final UserService userService;

    @PostMapping("/register")
    public Response<UserResponse> register(
            @RequestBody
            @Valid
            UserRegisterRequest request
    ) {

        UserResponse response = userService.save(request);
        return Response.OK(response);

    }

    @PostMapping("/login")
    public Response<TokenResponse> login(UserLoginRequest request){

        TokenResponse token = userService.login(request);
        return null; //Response.OK(token);


    }
}
