package com.goods.member.infrastructure.persistence;

import com.goods.member.domain.entity.Seller;
import com.goods.member.domain.repository.SellerRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerJpaRepository extends JpaRepository<Seller, UUID>, SellerRepository {

    Optional<Seller> findByMemberId(UUID memberId);

    boolean existsByMemberId(UUID memberId);
}
