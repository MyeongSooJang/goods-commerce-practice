package com.goods.payment.application.usecase;

import com.goods.payment.application.dto.WithdrawCommand;
import com.goods.payment.application.dto.WithdrawResult;

public interface WithdrawUseCase {

    WithdrawResult withdraw(WithdrawCommand command);
}
