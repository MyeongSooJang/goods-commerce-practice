package com.goods.order.infrastructure.repository;

import com.goods.order.domain.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClaimJpaRepository extends JpaRepository<Claim, UUID> {
}