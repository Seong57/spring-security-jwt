package com.example.sideporoject.domain.user.entity;

import com.example.sideporoject.domain.user.entity.enums.UserStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(name = "registered_at")
    private LocalDateTime registeredAt;

    @Column(name = "unregistered_at")
    private LocalDateTime unregisteredAt;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;
}
