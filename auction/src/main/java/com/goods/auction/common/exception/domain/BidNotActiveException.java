package com.goods.auction.common.exception.domain;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class BidNotActiveException extends CustomException {

    public BidNotActiveException() {
        super(ErrorCode.BID_NOT_ACTIVE);
    }
}
