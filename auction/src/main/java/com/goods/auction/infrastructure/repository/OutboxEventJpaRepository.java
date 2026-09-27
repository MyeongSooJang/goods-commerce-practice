package com.goods.auction.infrastructure.repository;

import com.goods.auction.domain.entity.OutboxEvent;
import com.goods.auction.domain.enumtype.OutboxEventStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxEventJpaRepository extends JpaRepository<OutboxEvent, UUID> {

    List<OutboxEvent> findAllByStatusOrderByCreatedAtAsc(OutboxEventStatus status);

}
