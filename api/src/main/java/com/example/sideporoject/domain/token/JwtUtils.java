package com.example.sideporoject.domain.token;

import com.example.sideporoject.domain.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class JwtUtils {

    private final TokenService tokenService;





}
