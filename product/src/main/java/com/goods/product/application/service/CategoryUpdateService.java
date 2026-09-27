package com.goods.product.application.service;

import com.goods.product.application.usecase.CategoryUpdateUseCase;
import com.goods.product.domain.entity.Category;
import com.goods.product.domain.repository.CategoryRepository;
import com.goods.product.presentation.dto.request.CategoryUpdateRequest;
import com.goods.product.presentation.dto.response.CategoryResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryUpdateService implements CategoryUpdateUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse updateCategory(
            UUID categoryId,
            CategoryUpdateRequest request
    ) {
        Category category = categoryRepository.findById(categoryId);
        category.update(
                request.name(),
                request.description(),
                request.sortOrder()
        );
        return CategoryResponse.from(category);
    }

    @Override
    public CategoryResponse updateCategoryBySeller(
            UUID categoryId,
            String sellerId,
            CategoryUpdateRequest request
    ) {
        Category category = categoryRepository.findById(categoryId);
        category.validateSeller(UUID.fromString(sellerId));
        category.update(
                request.name(),
                request.description(),
                request.sortOrder()
        );
        return CategoryResponse.from(category);
    }
}
