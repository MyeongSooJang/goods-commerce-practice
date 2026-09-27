package com.goods.cartservice.wish.application.service;

import com.goods.cartservice.cart.application.usecase.CartUpdateUseCase;
import com.goods.cartservice.cart.presentation.dto.request.AddCartItemRequest;
import com.goods.cartservice.wish.application.usecase.WishDeleteUseCase;
import com.goods.cartservice.wish.domain.entity.Wish;
import com.goods.cartservice.wish.domain.repository.WishRepository;
import com.goods.cartservice.wish.presentation.exception.WishNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WishDeleteService implements WishDeleteUseCase {

    private final WishRepository wishRepository;
    private final CartUpdateUseCase cartUpdateUseCase;

    @Override
    public void moveToCart(UUID memberId, UUID wishId) {
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(WishNotFoundException::new);

        wish.validateWishOwner(memberId);

        AddCartItemRequest request = new AddCartItemRequest(wish.getProductId(), 1);
        cartUpdateUseCase.addCartItem(memberId, request);

        wishRepository.delete(wish);
    }
}
