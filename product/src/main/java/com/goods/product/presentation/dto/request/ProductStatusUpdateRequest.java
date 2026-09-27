package com.goods.product.presentation.dto.request;

import com.goods.product.domain.enumtype.ProductStatus;
import jakarta.validation.constraints.NotNull;

public record ProductStatusUpdateRequest(
        @NotNull(message = "상품 상태는 필수입니다")
        ProductStatus status
) {
}
