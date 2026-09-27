package com.goods.cart.wish.application.usecase;

import com.goods.cart.wish.presentation.dto.response.WishListResponse;
import java.util.UUID;

public interface WishSearchUseCase {
    WishListResponse findWishes(UUID memberId);
}
