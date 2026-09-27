package com.goods.ai.common.exception;

public class ProductDraftAssistImageTooLargeException extends CustomException {

    public ProductDraftAssistImageTooLargeException() {
        super(ErrorCode.AI_ASSIST_IMAGE_TOO_LARGE);
    }
}
