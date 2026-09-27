package com.goods.auction.common.exception.domain;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class HighestBidderRebidNotAllowedException extends CustomException {

    public HighestBidderRebidNotAllowedException() {
        super(ErrorCode.HIGHEST_BIDDER_CANNOT_REBID);
    }
}
