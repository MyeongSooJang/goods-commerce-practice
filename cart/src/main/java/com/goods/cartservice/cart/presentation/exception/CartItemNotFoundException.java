package com.goods.cartservice.cart.presentation.exception;

public class CartItemNotFoundException extends CustomException {

    public CartItemNotFoundException() {
        super(ErrorCode.CART_ITEM_NOT_FOUND);
    }
}
