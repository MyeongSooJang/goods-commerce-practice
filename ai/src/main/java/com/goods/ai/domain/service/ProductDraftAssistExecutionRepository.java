package com.goods.ai.domain.service;

import com.goods.ai.application.dto.ProductDraftAssistResult;
import java.time.Duration;
import java.util.Optional;

public interface ProductDraftAssistExecutionRepository {

    Optional<ProductDraftAssistResult> findCachedResult(String fingerprint);

    boolean tryLock(String fingerprint, Duration ttl);

    void cacheResult(String fingerprint, ProductDraftAssistResult result, Duration ttl);

    void unlock(String fingerprint);
}
