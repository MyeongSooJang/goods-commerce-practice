package com.goods.auction.common.exception.application;

import com.goods.auction.common.exception.CustomException;
import com.goods.auction.common.exception.ErrorCode;

public class BidderWalletNotFoundException extends CustomException {

    public BidderWalletNotFoundException() {
        super(ErrorCode.BIDDER_WALLET_NOT_FOUND);
    }
}
