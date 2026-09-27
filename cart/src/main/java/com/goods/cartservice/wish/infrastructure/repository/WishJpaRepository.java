package com.goods.cartservice.wish.infrastructure.repository;

import com.goods.cartservice.wish.domain.entity.Wish;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishJpaRepository extends JpaRepository<Wish, UUID> {
    List<Wish> findByMemberId(UUID memberId);
    void deleteByMemberIdAndProductId(UUID memberId, UUID productId);
    boolean existsByMemberIdAndProductId(UUID memberId, UUID productId);
}
