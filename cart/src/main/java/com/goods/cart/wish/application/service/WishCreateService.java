package com.goods.cart.wish.application.service;

import com.goods.cart.wish.application.usecase.WishCreateUseCase;
import com.goods.cart.wish.domain.entity.Wish;
import com.goods.cart.wish.domain.repository.WishRepository;
import com.goods.cart.wish.presentation.dto.response.WishToggleResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WishCreateService implements WishCreateUseCase {

    private final WishRepository wishRepository;

    @Override
    public WishToggleResponse toggleWish(UUID memberId, UUID productId) {
        if (wishRepository.existsByMemberIdAndProductId(memberId, productId)) {
            wishRepository.deleteByMemberIdAndProductId(memberId, productId);
            return new WishToggleResponse(false);
        }

        Wish wish = Wish.create(memberId, productId);
        wishRepository.save(wish);

        return new WishToggleResponse(true);
    }
}
