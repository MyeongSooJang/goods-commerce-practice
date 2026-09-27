package com.goods.cart.cart.presentation.exception;

public class CartLimitExceededException extends CustomException {

    public CartLimitExceededException() {
        super(ErrorCode.CART_LIMIT_EXCEEDED);
    }
}
