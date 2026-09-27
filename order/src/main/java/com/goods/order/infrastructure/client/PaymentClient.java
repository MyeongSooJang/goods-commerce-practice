package com.goods.order.infrastructure.client;

import com.goods.order.infrastructure.client.dto.request.ExternalPaymentRefundRequest;
import com.goods.order.infrastructure.client.dto.request.ExternalPaymentRequest;
import com.goods.order.infrastructure.client.dto.request.ExternalSellerRefundRequest;
import com.goods.order.infrastructure.client.dto.response.PaymentRefundResultResponse;
import com.goods.order.infrastructure.client.dto.response.PaymentResultResponse;
import com.goods.order.presentation.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "${services.payment.url:http://localhost:8082}", path = "/api/payments")
public interface PaymentClient {

    @PostMapping("/orders")
    ApiResponse<PaymentResultResponse> requestPayment(@RequestBody ExternalPaymentRequest paymentInfo);

    @PostMapping("/cancellations")
    ApiResponse<PaymentRefundResultResponse> requestRefund(@RequestBody ExternalPaymentRefundRequest request);

    @PostMapping("/seller/refunds/confirm")
    ApiResponse<PaymentRefundResultResponse> requestSellerRefund(@RequestBody ExternalSellerRefundRequest request);
}
