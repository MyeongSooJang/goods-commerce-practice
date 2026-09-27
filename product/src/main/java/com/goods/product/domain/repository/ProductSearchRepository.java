package com.goods.product.domain.repository;

import com.goods.product.domain.entity.Product;
import com.goods.product.domain.model.ProductSearchResult;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearchRepository {

    Page<ProductSearchResult> searchProducts(
            List<UUID> categoryIds,
            String keyword,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Pageable pageable
    );

    void index(Product product, List<String> allCategoryIds, String thumbnailS3Key);

    void delete(UUID productId);

    Set<UUID> findExistingIds(Collection<UUID> productIds);
}
