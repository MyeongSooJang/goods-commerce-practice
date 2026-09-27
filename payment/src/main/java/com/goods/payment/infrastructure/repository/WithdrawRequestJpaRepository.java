package com.goods.payment.infrastructure.repository;

import com.goods.payment.domain.entity.WithdrawRequest;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRequestJpaRepository extends JpaRepository<WithdrawRequest, UUID> {

    Page<WithdrawRequest> findByMemberId(UUID memberId, Pageable pageable);
}
