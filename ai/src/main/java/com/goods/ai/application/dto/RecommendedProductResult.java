package com.goods.ai.application.dto;

import java.util.UUID;

public record RecommendedProductResult(
        UUID productId,
        double similarityScore
) {
}

