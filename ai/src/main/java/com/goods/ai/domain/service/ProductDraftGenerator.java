package com.goods.ai.domain.service;

import com.goods.ai.application.dto.ProductDraftAssistCommand;
import com.goods.ai.application.dto.ProductDraftAssistResult;

public interface ProductDraftGenerator {

    ProductDraftAssistResult generate(ProductDraftAssistCommand command);
}
