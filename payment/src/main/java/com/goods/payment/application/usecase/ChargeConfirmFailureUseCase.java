package com.goods.payment.application.usecase;

import com.goods.payment.application.dto.ChargeConfirmFailureCommand;
import com.goods.payment.application.dto.ChargeConfirmFailureResult;

public interface ChargeConfirmFailureUseCase {

    ChargeConfirmFailureResult confirmChargeFailure(ChargeConfirmFailureCommand command);
}
