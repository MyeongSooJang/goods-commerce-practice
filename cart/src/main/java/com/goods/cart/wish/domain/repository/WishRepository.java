package com.goods.cart.wish.domain.repository;

import com.goods.cart.wish.domain.entity.Wish;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WishRepository {

    Wish save(Wish wish);

    Optional<Wish> findById(UUID wishId);

    List<Wish> findByMemberId(UUID memberId);

    void delete(Wish wish);

    void deleteByMemberIdAndProductId(UUID memberId, UUID productId);

    boolean existsByMemberIdAndProductId(UUID memberId, UUID productId);
}
