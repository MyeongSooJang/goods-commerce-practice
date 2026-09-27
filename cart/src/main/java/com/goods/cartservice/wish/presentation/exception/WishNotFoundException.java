package com.goods.cartservice.wish.presentation.exception;

public class WishNotFoundException extends CustomException {

    public WishNotFoundException() {
        super(ErrorCode.WISH_NOT_FOUND);
    }
}
