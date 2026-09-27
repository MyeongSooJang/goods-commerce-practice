package com.goods.ai.common.exception;

public class ProductDraftAssistInputFieldsRequiredException extends CustomException {

    public ProductDraftAssistInputFieldsRequiredException() {
        super(ErrorCode.AI_ASSIST_INPUT_FIELDS_REQUIRED);
    }
}
