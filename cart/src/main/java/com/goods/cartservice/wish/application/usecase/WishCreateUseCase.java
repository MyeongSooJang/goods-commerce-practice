package com.goods.cartservice.wish.application.usecase;

import com.goods.cartservice.wish.presentation.dto.response.WishToggleResponse;
import java.util.UUID;

public interface WishCreateUseCase {
    WishToggleResponse toggleWish(UUID memberId, UUID productId);
}
