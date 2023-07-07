package com.example.fakeshopapi.dto;

import lombok.Data;

import jakarta.validation.constraints.*;

@Data
public class RefreshTokenDto {
    @NotEmpty
    String refreshToken;
}
