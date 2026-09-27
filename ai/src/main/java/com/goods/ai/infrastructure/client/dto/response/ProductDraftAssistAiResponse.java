package com.goods.ai.infrastructure.client.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record ProductDraftAssistAiResponse(
        String suggestedTitle,
        String suggestedDescription,
        BigDecimal suggestedPrice,
        List<String> suggestedKeywords,
        String notes
) {
}
