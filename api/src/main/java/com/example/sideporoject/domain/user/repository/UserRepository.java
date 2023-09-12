package com.example.sideporoject.domain.user.repository;

import com.example.sideporoject.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
