package com.goods.ai.application.usecase;

import com.goods.ai.application.dto.EmbeddingAdminResult;

public interface EmbeddingAdminUseCase {

    EmbeddingAdminResult backfillMissing();

    EmbeddingAdminResult reindexAll();
}

