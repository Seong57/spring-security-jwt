package com.example.sideporoject.domain.health;

import com.example.sideporoject.commom.costomresponse.reponse.Response;
import com.example.sideporoject.domain.health.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HealthCall {

    @GetMapping("/health")
    public Response<HealthResponse> health() {

        HealthResponse health = HealthResponse.builder()
            .id(1L)
            .name("health")
            .registeredAt(LocalDateTime.now())
            .build();

        return Response.OK(health);
    }
}
