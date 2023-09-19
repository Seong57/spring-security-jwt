package com.example.sideporoject.domain.token.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenDto {

    @NotEmpty
    private String refreshToken;
}
