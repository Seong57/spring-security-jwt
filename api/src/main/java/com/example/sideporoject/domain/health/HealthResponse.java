package com.example.sideporoject.domain.health;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HealthResponse {

    private Long id;
    private String name;
    private LocalDateTime registeredAt;
}
