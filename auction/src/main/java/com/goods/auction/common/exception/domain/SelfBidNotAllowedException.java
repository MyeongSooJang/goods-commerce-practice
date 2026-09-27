package com.goods.auction.common.exception.domain;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class SelfBidNotAllowedException extends CustomException {

    public SelfBidNotAllowedException() {
        super(ErrorCode.SELF_BID_NOT_ALLOWED);
    }
}
