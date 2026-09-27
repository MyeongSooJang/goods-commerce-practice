package com.goods.ai.application.usecase;

import com.goods.ai.application.dto.ProductDraftAssistCommand;
import com.goods.ai.application.dto.ProductDraftAssistResult;

public interface ProductDraftAssistUseCase {

    ProductDraftAssistResult createProductDraft(ProductDraftAssistCommand command);
}
