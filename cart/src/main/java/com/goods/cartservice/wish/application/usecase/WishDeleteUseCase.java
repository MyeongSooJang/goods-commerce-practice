package com.goods.cartservice.wish.application.usecase;

import java.util.UUID;

public interface WishDeleteUseCase {
    void moveToCart(UUID memberId, UUID wishId);
}
