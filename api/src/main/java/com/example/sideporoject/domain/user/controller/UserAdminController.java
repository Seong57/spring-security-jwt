package com.example.sideporoject.domain.user.controller;

import com.example.sideporoject.commom.costomresponse.reponse.Response;
import com.example.sideporoject.domain.health.HealthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class UserAdminController {

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
