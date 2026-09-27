package com.goods.product.application.service;

import com.goods.product.application.usecase.ProductImageUpdateUseCase;
import com.goods.product.common.exception.ImageNotOwnedByProductException;
import com.goods.product.common.exception.ProductImageNotFoundException;
import com.goods.product.common.exception.ProductNotFoundException;
import com.goods.product.domain.entity.Product;
import com.goods.product.domain.entity.ProductImage;
import com.goods.product.domain.repository.ProductImageRepository;
import com.goods.product.domain.repository.ProductRepository;
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
public class ProductImageUpdateService implements ProductImageUpdateUseCase {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOutboxEventService productOutboxEventService;

    @Override
    public void changeThumbnail(UUID productId, UUID imageId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        ProductImage newThumbnail = productImageRepository.findById(imageId)
                .orElseThrow(ProductImageNotFoundException::new);

        if (!newThumbnail.getProductId().equals(productId)) {
            throw new ImageNotOwnedByProductException();
        }

        productImageRepository.findThumbnailByProductId(productId)
                .ifPresent(current -> {
                    current.unmarkThumbnail();
                    productImageRepository.save(current);
                });

        newThumbnail.markAsThumbnail();
        productImageRepository.save(newThumbnail);

        productOutboxEventService.saveUpdatedEvent(product);
        productOutboxEventService.saveThumbnailChangedEvent(productId, newThumbnail.getS3Key());

        log.info("Thumbnail changed: productId={}, imageId={}", productId, imageId);
    }
}
