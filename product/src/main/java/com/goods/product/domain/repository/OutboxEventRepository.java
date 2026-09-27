package com.goods.product.domain.repository;

import com.goods.product.domain.entity.OutboxEvent;
import com.goods.product.domain.enumtype.OutboxEventStatus;
import java.util.List;
import java.util.UUID;

public interface OutboxEventRepository {

    OutboxEvent save(OutboxEvent outboxEvent);

    List<OutboxEvent> findAllByStatus(OutboxEventStatus status);

    int changeToProcessingIfPending(UUID id);

    int changeToPublishedIfProcessing(UUID id);

    int changeToPendingIfProcessing(UUID id);

    int recoverStuckProcessingEvents(java.time.LocalDateTime threshold);
}
