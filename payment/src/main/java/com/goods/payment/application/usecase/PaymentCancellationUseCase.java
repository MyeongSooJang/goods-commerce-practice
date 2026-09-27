package com.goods.payment.application.usecase;

import com.goods.payment.application.dto.PaymentRefundCommand;
import com.goods.payment.application.dto.PaymentRefundResult;

public interface PaymentCancellationUseCase {

    PaymentRefundResult requestCancellation(PaymentRefundCommand command);
}
