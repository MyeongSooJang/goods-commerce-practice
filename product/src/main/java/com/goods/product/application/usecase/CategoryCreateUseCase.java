package com.goods.product.application.usecase;

import com.goods.product.presentation.dto.request.CategoryCreateRequest;
import com.goods.product.presentation.dto.response.CategoryResponse;

public interface CategoryCreateUseCase {
    CategoryResponse createCategory(CategoryCreateRequest request);

    CategoryResponse createCategoryBySeller(String sellerId, CategoryCreateRequest request);
}
