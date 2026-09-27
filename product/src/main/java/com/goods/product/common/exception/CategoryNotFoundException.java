package com.goods.product.common.exception;

public class CategoryNotFoundException extends CustomException {
    public CategoryNotFoundException() {
        super(ErrorCode.CATEGORY_NOT_FOUND);
    }
}
