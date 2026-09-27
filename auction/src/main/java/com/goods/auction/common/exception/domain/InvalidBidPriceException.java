package com.goods.auction.common.exception.domain;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class InvalidBidPriceException extends CustomException {

    public InvalidBidPriceException() {
        super(ErrorCode.INVALID_BID_PRICE);
    }
}
