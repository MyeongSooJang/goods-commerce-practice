package com.goods.payment.common.exception;

public class InsufficientWalletBalanceException extends CustomException {

    public InsufficientWalletBalanceException() {
        super(ErrorCode.INSUFFICIENT_WALLET_BALANCE);
    }
}
