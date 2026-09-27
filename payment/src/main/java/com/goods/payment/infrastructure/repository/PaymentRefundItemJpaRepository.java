package com.goods.payment.infrastructure.repository;

import com.goods.payment.domain.entity.PaymentRefundItem;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRefundItemJpaRepository extends JpaRepository<PaymentRefundItem, UUID> {

    List<PaymentRefundItem> findAllByRefundId(UUID refundId);
}
