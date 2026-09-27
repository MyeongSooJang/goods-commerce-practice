package com.goods.payment.presentation.controller;

import com.goods.payment.application.dto.PagedResult;
import com.goods.payment.application.dto.PendingSellerIncomeItemResult;
import com.goods.payment.application.dto.WithdrawListItemResult;
import com.goods.payment.application.usecase.PaymentSearchUseCase;
import com.goods.payment.domain.enumtype.WithdrawStatus;
import com.goods.payment.presentation.dto.response.ApiResponse;
import com.goods.payment.presentation.dto.response.PaymentSellerWithdrawalSummaryResponse;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/payments")
public class PaymentInternalController {

    private final PaymentSearchUseCase paymentSearchUseCase;

    public PaymentInternalController(PaymentSearchUseCase paymentSearchUseCase) {
        this.paymentSearchUseCase = paymentSearchUseCase;
    }

    @GetMapping("/sellers/{sellerId}/withdrawal-summary")
    public ResponseEntity<ApiResponse<PaymentSellerWithdrawalSummaryResponse>> getSellerWithdrawalSummary(
            @PathVariable UUID sellerId
    ) {
        PagedResult<PendingSellerIncomeItemResult> pendingIncomes =
                paymentSearchUseCase.findAllPendingSellerIncomes(sellerId, 0, 1);
        PagedResult<WithdrawListItemResult> withdrawRequests =
                paymentSearchUseCase.findAllWithdrawRequests(sellerId, 0, 20);

        boolean hasPendingWithdrawRequest = withdrawRequests.items().stream()
                .map(WithdrawListItemResult::status)
                .anyMatch(status -> status == WithdrawStatus.REQUESTED || status == WithdrawStatus.PROCESSING);

        return ResponseEntity.ok(ApiResponse.success(
                new PaymentSellerWithdrawalSummaryResponse(
                        pendingIncomes.totalElements() > 0,
                        hasPendingWithdrawRequest
                )
        ));
    }
}
