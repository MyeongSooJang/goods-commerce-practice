package com.goods.ai.domain.model;

import java.util.UUID;

public record SimilarProductMatch(
        UUID productId,
        double distance
) {
}

