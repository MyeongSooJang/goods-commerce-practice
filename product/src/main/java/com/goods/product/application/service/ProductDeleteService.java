package com.goods.product.application.service;

import com.goods.product.application.usecase.ProductDeleteUseCase;
import com.goods.product.common.exception.ProductNotFoundException;
import com.goods.product.domain.entity.Product;
import com.goods.product.domain.repository.ProductRepository;
import com.goods.product.domain.repository.ProductSearchRepository;
import com.goods.product.infrastructure.messaging.kafka.ProductOutboxEventService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductDeleteService implements ProductDeleteUseCase {

    private final ProductRepository productRepository;
    private final ProductOutboxEventService productOutboxEventService;
    private final ProductSearchRepository productSearchRepository;

    @Override
    public void deleteProduct(String sellerId, String productId) {
        Product product = productRepository.findById(UUID.fromString(productId))
                .orElseThrow(ProductNotFoundException::new);

        product.validateSeller(UUID.fromString(sellerId));

        product.delete();

        deleteFromEsDirectly(product.getProductId());

        productOutboxEventService.saveDeletedEvent(product);
    }

    private void deleteFromEsDirectly(UUID productId) {
        try {
            productSearchRepository.delete(productId);
            log.info("ES 직접 삭제 완료: productId={}", productId);
        } catch (Exception e) {
            log.error("ES 직접 삭제 실패 - outbox/reconciliation 으로 복구 위임: productId={}",
                    productId, e);
        }
    }
}
