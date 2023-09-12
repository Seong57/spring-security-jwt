package com.example.sideporoject.domain.user.controller.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email(message = "이메일 형식을 확인해주세요")
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String address;
}
