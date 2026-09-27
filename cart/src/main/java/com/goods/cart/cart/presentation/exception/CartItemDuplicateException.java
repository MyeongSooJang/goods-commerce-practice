package com.goods.cart.cart.presentation.exception;

public class CartItemDuplicateException extends CustomException {

    public CartItemDuplicateException() {
        super(ErrorCode.CART_ITEM_DUPLICATE);
    }
}
