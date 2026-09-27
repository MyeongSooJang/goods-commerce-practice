package com.goods.cart.wish.application.service;

import com.goods.cart.wish.application.usecase.WishSearchUseCase;
import com.goods.cart.wish.domain.entity.Wish;
import com.goods.cart.wish.domain.repository.WishRepository;
import com.goods.cart.wish.presentation.dto.response.WishListResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WishSearchService implements WishSearchUseCase {

    private final WishRepository wishRepository;

    @Override
    public WishListResponse findWishes(UUID memberId) {
        List<Wish> wishes = wishRepository.findByMemberId(memberId);
        List<UUID> productIds = wishes.stream()
            .map(Wish::getProductId)
            .collect(Collectors.toList());

        return new WishListResponse(productIds, productIds.size());
    }
}
