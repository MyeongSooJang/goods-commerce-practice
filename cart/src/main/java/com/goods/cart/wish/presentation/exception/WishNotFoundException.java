package com.goods.cart.wish.presentation.exception;

public class WishNotFoundException extends CustomException {

    public WishNotFoundException() {
        super(ErrorCode.WISH_NOT_FOUND);
    }
}
