package com.goods.product.application.service;

import com.goods.product.application.usecase.CategorySearchUseCase;
import com.goods.product.domain.entity.Category;
import com.goods.product.domain.repository.CategoryRepository;
import com.goods.product.presentation.dto.response.CategoryResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategorySearchService implements CategorySearchUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponse::from)
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId);
        return CategoryResponse.from(category);
    }

    @Override
    public List<CategoryResponse> getCategoriesByDepth(Integer depth) {
        return categoryRepository.findByDepth(depth).stream()
                .map(CategoryResponse::from)
                .toList();
    }

    @Override
    public List<CategoryResponse> getChildCategories(UUID parentId) {
        return categoryRepository.findByParentId(parentId).stream()
                .map(CategoryResponse::from)
                .toList();
    }
}
