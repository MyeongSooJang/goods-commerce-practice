package com.goods.auction.common.exception.application;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class InsufficientDepositException extends CustomException {

    public InsufficientDepositException() {
        super(ErrorCode.INSUFFICIENT_DEPOSIT);
    }
}
