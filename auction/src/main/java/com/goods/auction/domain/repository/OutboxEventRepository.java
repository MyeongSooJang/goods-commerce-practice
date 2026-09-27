package com.goods.auction.domain.repository;

import com.goods.auction.domain.entity.OutboxEvent;
import com.goods.auction.domain.enumtype.OutboxEventStatus;
import java.util.List;

public interface OutboxEventRepository {

    OutboxEvent save(OutboxEvent outboxEvent);

    List<OutboxEvent> findAllByStatus(OutboxEventStatus status);
}
