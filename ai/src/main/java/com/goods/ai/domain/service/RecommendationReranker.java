package com.goods.ai.domain.service;

import com.goods.ai.application.dto.RecommendedProductResult;
import java.util.List;
import java.util.UUID;

public interface RecommendationReranker {

    List<UUID> rerank(UUID baseProductId, List<RecommendedProductResult> candidates, int selectCount);
}
