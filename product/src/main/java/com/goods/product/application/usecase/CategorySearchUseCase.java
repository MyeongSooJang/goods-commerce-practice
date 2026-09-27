package com.goods.product.application.usecase;

import com.goods.product.presentation.dto.response.CategoryResponse;
import java.util.List;
import java.util.UUID;

public interface CategorySearchUseCase {
    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(UUID categoryId);

    List<CategoryResponse> getCategoriesByDepth(Integer depth);

    List<CategoryResponse> getChildCategories(UUID parentId);
}
