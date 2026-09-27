package com.goods.cart.wish.application.usecase;

import com.goods.cart.wish.presentation.dto.response.WishToggleResponse;
import java.util.UUID;

public interface WishCreateUseCase {
    WishToggleResponse toggleWish(UUID memberId, UUID productId);
}
