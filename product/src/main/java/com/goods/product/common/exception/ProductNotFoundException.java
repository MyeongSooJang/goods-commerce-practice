package com.goods.product.common.exception;

public class ProductNotFoundException extends CustomException {

    public ProductNotFoundException() {
        super(ErrorCode.PRODUCT_NOT_FOUND);
    }
}
