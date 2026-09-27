package com.goods.product.common.exception;

public class SellerNotAuthorizedException extends CustomException {
    public SellerNotAuthorizedException() {
        super(ErrorCode.SELLER_NOT_AUTHORIZED);
    }
}