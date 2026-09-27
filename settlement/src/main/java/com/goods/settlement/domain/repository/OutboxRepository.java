package com.goods.settlement.domain.repository;

import com.goods.settlement.domain.entity.OutboxEvent;
import com.goods.settlement.domain.enumtype.OutboxStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OutboxRepository {

    OutboxEvent save(OutboxEvent event);

    Optional<OutboxEvent> findById(UUID id);

    List<OutboxEvent> findByStatus(OutboxStatus status);

    int changeToProcessingIfPending(UUID id);

    int changeToPublishedIfProcessing(UUID id);

    int revertStuckProcessingToPending(LocalDateTime threshold);
}
