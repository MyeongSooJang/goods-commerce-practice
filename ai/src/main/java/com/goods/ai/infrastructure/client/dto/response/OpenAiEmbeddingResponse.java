package com.goods.ai.infrastructure.client.dto.response;

import java.util.List;

public record OpenAiEmbeddingResponse(
        List<EmbeddingData> data
) {
    public record EmbeddingData(
            List<Float> embedding
    ) {
    }
}
