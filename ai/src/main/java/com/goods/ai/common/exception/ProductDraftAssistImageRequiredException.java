package com.goods.ai.common.exception;

public class ProductDraftAssistImageRequiredException extends CustomException {

    public ProductDraftAssistImageRequiredException() {
        super(ErrorCode.AI_ASSIST_IMAGE_REQUIRED);
    }
}
