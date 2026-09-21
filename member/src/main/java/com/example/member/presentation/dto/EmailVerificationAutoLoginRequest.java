package com.example.member.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailVerificationAutoLoginRequest(
        @NotBlank
        String autoLoginToken
) {
}
