package com.goods.cartservice.wish.application.usecase;

import com.goods.cartservice.wish.presentation.dto.response.WishListResponse;
import java.util.UUID;

public interface WishSearchUseCase {
    WishListResponse findWishes(UUID memberId);
}
