package com.goods.payment.common.exception;

public class WithdrawAmountBelowMinimumException extends CustomException {

    public WithdrawAmountBelowMinimumException() {
        super(ErrorCode.WITHDRAW_AMOUNT_BELOW_MINIMUM);
    }
}
