package com.goods.member.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record WithdrawMemberRequest(
        @NotBlank
        String currentPassword
) {
}
