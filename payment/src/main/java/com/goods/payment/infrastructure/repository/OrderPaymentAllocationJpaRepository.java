package com.goods.payment.infrastructure.repository;

import com.goods.payment.domain.entity.OrderPaymentAllocation;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPaymentAllocationJpaRepository extends JpaRepository<OrderPaymentAllocation, UUID> {

    List<OrderPaymentAllocation> findAllByOrderPaymentId(UUID orderPaymentId);
}
