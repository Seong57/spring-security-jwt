package com.example.sideporoject.domain.user.controller;

import com.example.sideporoject.commom.costomresponse.reponse.Response;
import com.example.sideporoject.domain.auth.UserDetailsImpl;
import com.example.sideporoject.domain.health.HealthResponse;
import com.example.sideporoject.domain.token.model.RefreshTokenDto;
import com.example.sideporoject.domain.token.service.RefreshTokenService;
import com.example.sideporoject.domain.user.controller.model.UserResponse;
import com.example.sideporoject.domain.user.service.UserService;
import com.example.sideporoject.domain.token.model.TokenResponse;
import com.example.sideporoject.security.token.service.TokenService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final TokenService tokenService;

    @GetMapping("/me")
    public Response<UserResponse> me(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            UserDetailsImpl userDetails
    ){
        UserResponse response = UserResponse.builder()
                .name(userDetails.getName())
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

    @DeleteMapping("/logout")
    public ResponseEntity<RefreshTokenDto> logout(
            @RequestBody
            @Valid
            RefreshTokenDto refreshTokenDto
    ){
        refreshTokenService.deleteRefreshToken(refreshTokenDto);

        return ResponseEntity.status(200).body(refreshTokenDto);
    }

    @PostMapping("/refresh-token")
    public Response<TokenResponse> refreshToken(
            @RequestBody
            RefreshTokenDto refreshTokenDto
    ){
        TokenResponse tokenResponse = refreshTokenService.reissueAccessToken(refreshTokenDto);
        return Response.OK(tokenResponse);
    }
}
