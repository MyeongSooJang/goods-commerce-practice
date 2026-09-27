package com.goods.auction.common.exception.domain;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class AuctionNotOngoingException extends CustomException {

    public AuctionNotOngoingException() {
        super(ErrorCode.AUCTION_NOT_ONGOING);
    }
}
