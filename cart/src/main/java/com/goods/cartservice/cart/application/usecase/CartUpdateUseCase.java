package com.goods.cartservice.cart.application.usecase;

import com.goods.cartservice.cart.presentation.dto.request.AddCartItemRequest;
import com.goods.cartservice.cart.presentation.dto.request.UpdateCartItemRequest;
import com.goods.cartservice.cart.presentation.dto.response.CartResponse;
import java.util.UUID;

public interface CartUpdateUseCase {
    CartResponse addCartItem(UUID memberId, AddCartItemRequest request);
    CartResponse updateCartItem(UUID memberId, UUID cartId, UpdateCartItemRequest request);
}
