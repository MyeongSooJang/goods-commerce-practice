package com.goods.auction.common.exception.application;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class BidNotFoundException extends CustomException {

    public BidNotFoundException() {
        super(ErrorCode.BID_NOT_FOUND);
    }
}
