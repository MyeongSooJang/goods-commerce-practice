package com.goods.payment.application.usecase;

import com.goods.payment.application.dto.OrderPaymentValidationCommand;
import com.goods.payment.domain.service.OrderPaymentValidationData;

public interface OrderPaymentValidationUseCase {

    OrderPaymentValidationData validateOrderPayment(OrderPaymentValidationCommand command);
}
