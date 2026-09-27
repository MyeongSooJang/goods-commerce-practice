package com.goods.payment.presentation.dto.response;

public record PaymentSellerWithdrawalSummaryResponse(
        boolean hasPendingIncome,
        boolean hasPendingWithdrawRequest
) {
}
