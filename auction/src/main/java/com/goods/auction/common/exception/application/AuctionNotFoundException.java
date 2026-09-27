package com.goods.auction.common.exception.application;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class AuctionNotFoundException extends CustomException {

    public AuctionNotFoundException() {
        super(ErrorCode.AUCTION_NOT_FOUND);
    }
}
