package com.goods.payment.infrastructure.repository;

import com.goods.payment.domain.entity.OrderPaymentAllocation;
import com.goods.payment.domain.repository.OrderPaymentAllocationRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class OrderPaymentAllocationRepositoryImpl implements OrderPaymentAllocationRepository {

    private final OrderPaymentAllocationJpaRepository orderPaymentAllocationJpaRepository;

    public OrderPaymentAllocationRepositoryImpl(
            OrderPaymentAllocationJpaRepository orderPaymentAllocationJpaRepository
    ) {
        this.orderPaymentAllocationJpaRepository = orderPaymentAllocationJpaRepository;
    }

    @Override
    public List<OrderPaymentAllocation> saveAll(List<OrderPaymentAllocation> allocations) {
        return orderPaymentAllocationJpaRepository.saveAll(allocations);
    }

    @Override
    public List<OrderPaymentAllocation> findAllByOrderPaymentId(UUID orderPaymentId) {
        return orderPaymentAllocationJpaRepository.findAllByOrderPaymentId(orderPaymentId);
    }
}
