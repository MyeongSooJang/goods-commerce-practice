package com.example.member.domain.repository;

import com.example.member.domain.entity.Seller;
import java.util.Optional;
import java.util.UUID;

public interface SellerRepository {

    Seller save(Seller seller);

    Optional<Seller> findByMemberId(UUID memberId);

    boolean existsByMemberId(UUID memberId);
}
