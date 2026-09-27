package com.goods.ai.common.exception;

public class ProductDraftAssistImageCountExceededException extends CustomException {

    public ProductDraftAssistImageCountExceededException() {
        super(ErrorCode.AI_ASSIST_IMAGE_COUNT_EXCEEDED);
    }
}
