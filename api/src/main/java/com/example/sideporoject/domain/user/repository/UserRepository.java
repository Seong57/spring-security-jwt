package com.example.sideporoject.domain.user.repository;

import com.example.sideporoject.domain.user.entity.UserEntity;
import com.example.sideporoject.domain.user.entity.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByEmailAndPasswordAndStatusOrderByIdDesc(
            String email, String password, UserStatus status);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
