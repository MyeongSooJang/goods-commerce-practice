package com.goods.payment.application.usecase;

import com.goods.payment.application.dto.CardPaymentConfirmCommand;
import com.goods.payment.application.dto.CardPaymentConfirmResult;

public interface CardPaymentConfirmUseCase {

    CardPaymentConfirmResult confirmCardPayment(CardPaymentConfirmCommand command);
}
