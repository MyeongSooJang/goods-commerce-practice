package com.goods.payment.application.usecase;

import com.goods.payment.application.dto.ChargeConfirmCommand;
import com.goods.payment.application.dto.ChargeConfirmResult;

/**
 * 충전 승인 유스케이스의 진입점이다.
 */
public interface ChargeConfirmUseCase {

    ChargeConfirmResult confirmCharge(ChargeConfirmCommand command);
}
