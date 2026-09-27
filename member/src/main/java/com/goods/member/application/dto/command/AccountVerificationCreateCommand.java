package com.goods.member.application.dto.command;

public record AccountVerificationCreateCommand(
        String bankName,
        String accountNumber
) {
}
