package com.goods.auction.common.exception.application;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class DepositStateConflictException extends CustomException {

    public DepositStateConflictException() {
        super(ErrorCode.DEPOSIT_STATE_CONFLICT);
    }
}
