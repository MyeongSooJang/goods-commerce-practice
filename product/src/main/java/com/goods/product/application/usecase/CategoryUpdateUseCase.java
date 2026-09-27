package com.goods.product.application.usecase;

import com.goods.product.presentation.dto.request.CategoryUpdateRequest;
import com.goods.product.presentation.dto.response.CategoryResponse;
import java.util.UUID;

public interface CategoryUpdateUseCase {
    CategoryResponse updateCategory(UUID categoryId, CategoryUpdateRequest request);

    CategoryResponse updateCategoryBySeller(UUID categoryId, String sellerId, CategoryUpdateRequest request);
}
