package com.example.sideporoject.domain.user.controller.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    @Email(message = "이메일 형식으로 입력하세요")
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
