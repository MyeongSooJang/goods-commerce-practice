package com.goods.auction.common.exception.application;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class PreviousDepositNotFoundException extends CustomException {

    public PreviousDepositNotFoundException() {
        super(ErrorCode.PREVIOUS_DEPOSIT_NOT_FOUND);
    }
}
