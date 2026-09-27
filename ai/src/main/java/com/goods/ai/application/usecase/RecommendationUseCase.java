package com.goods.ai.application.usecase;

import com.goods.ai.application.dto.RecommendedProductResult;
import java.util.List;
import java.util.UUID;

public interface RecommendationUseCase {

    List<RecommendedProductResult> recommend(UUID productId);
}

