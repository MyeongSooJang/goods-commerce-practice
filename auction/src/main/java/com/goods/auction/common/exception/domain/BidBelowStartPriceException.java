package com.goods.auction.common.exception.domain;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class BidBelowStartPriceException extends CustomException {

    public BidBelowStartPriceException() {
        super(ErrorCode.BID_BELOW_START_PRICE);
    }
}
