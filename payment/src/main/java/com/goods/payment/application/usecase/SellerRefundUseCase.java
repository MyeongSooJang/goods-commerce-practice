package com.goods.payment.application.usecase;

import com.goods.payment.application.dto.PaymentRefundResult;
import com.goods.payment.application.dto.SellerRefundCommand;

public interface SellerRefundUseCase {

    PaymentRefundResult requestSellerRefund(SellerRefundCommand command);
}
