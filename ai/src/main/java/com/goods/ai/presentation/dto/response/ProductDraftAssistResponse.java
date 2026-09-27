package com.goods.ai.presentation.dto.response;

import com.goods.ai.application.dto.ProductDraftAssistResult;
import java.math.BigDecimal;
import java.util.List;

public record ProductDraftAssistResponse(
        String suggestedTitle,
        String suggestedDescription,
        BigDecimal suggestedPrice,
        List<String> suggestedKeywords,
        String notes
) {

    public static ProductDraftAssistResponse from(ProductDraftAssistResult result) {
        return new ProductDraftAssistResponse(
                result.suggestedTitle(),
                result.suggestedDescription(),
                result.suggestedPrice(),
                result.suggestedKeywords(),
                result.notes()
        );
    }
}
