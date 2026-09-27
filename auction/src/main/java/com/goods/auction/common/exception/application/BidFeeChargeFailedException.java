package com.goods.auction.common.exception.application;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class BidFeeChargeFailedException extends CustomException {

    public BidFeeChargeFailedException() {
        super(ErrorCode.BID_FEE_CHARGE_FAILED);
    }
}
