package com.goods.ai.application.dto;

public record ProductDraftAssistField(
        ProductDraftAssistFieldKey fieldKey,
        String fieldLabel,
        Integer maxLength,
        String currentValue
) {
}
