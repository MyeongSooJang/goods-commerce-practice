package com.goods.auction.common.exception.application;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class BidFeeChargeRequestInvalidException extends CustomException {

    public BidFeeChargeRequestInvalidException() {
        super(ErrorCode.BID_FEE_CHARGE_REQUEST_INVALID);
    }
}
