package com.example.member.infrastructure.persistence.jpa;

import com.example.member.domain.entity.Seller;
import com.example.member.domain.repository.SellerRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerJpaRepository extends JpaRepository<Seller, UUID>, SellerRepository {

    Optional<Seller> findByMemberId(UUID memberId);

    boolean existsByMemberId(UUID memberId);
}
