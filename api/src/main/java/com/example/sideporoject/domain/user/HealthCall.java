package com.example.sideporoject.domain.user;

import com.example.sideporoject.domain.user.health.HealthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HealthCall {

    @GetMapping("/health")
    public ResponseEntity<HealthResponse> health() {

        HealthResponse health = HealthResponse.builder()
            .id(1L)
            .name("health")
            .registeredAt(LocalDateTime.now())
            .build();

        return ResponseEntity.ok(health);
    }
}
