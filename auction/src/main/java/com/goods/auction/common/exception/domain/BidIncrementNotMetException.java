package com.goods.auction.common.exception.domain;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class BidIncrementNotMetException extends CustomException {

    public BidIncrementNotMetException() {
        super(ErrorCode.BID_INCREMENT_NOT_MET);
    }
}
