package com.goods.payment.application.dto;

public record ChargeConfirmFailureCommand(
        String orderId,
        String failureCode,
        String failureMessage
) {
}
