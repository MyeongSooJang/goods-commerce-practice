package com.goods.ai.application.usecase;

import com.goods.ai.application.dto.ProductDeactivateCommand;
import com.goods.ai.application.dto.ProductEmbeddingCommand;

public interface ProductEmbeddingUseCase {

    void embedding(ProductEmbeddingCommand command);

    void deactivate(ProductDeactivateCommand command);
}
