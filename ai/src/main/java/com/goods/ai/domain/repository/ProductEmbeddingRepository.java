package com.goods.ai.domain.repository;

import com.goods.ai.domain.entity.ProductEmbedding;
import com.goods.ai.domain.model.SimilarProductMatch;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductEmbeddingRepository {

    Optional<ProductEmbedding> findByProductId(UUID productId);

    List<SimilarProductMatch> findSimilarActive(UUID productId, String embeddingVector, int limit);

    ProductEmbedding save(ProductEmbedding productEmbedding);
}
