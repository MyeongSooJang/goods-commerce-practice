package com.example.member.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record ConfirmEmailVerificationRequest(
        @NotBlank
        String token
) {
}

