package com.goods.notification.infrastructure.repository;

import com.goods.notification.domain.entity.Notification;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJpaRepository extends JpaRepository<Notification, UUID> {

    Page<Notification> findAllByMemberIdOrderByCreatedAtDesc(UUID memberId, Pageable pageable);

    boolean existsByEventIdAndMemberIdAndType(UUID eventId, UUID memberId, com.goods.notification.domain.enumtype.NotificationType type);

    long countByMemberIdAndReadFalse(UUID memberId);
}
