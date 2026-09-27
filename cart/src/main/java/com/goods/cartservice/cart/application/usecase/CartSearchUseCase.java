package com.goods.cartservice.cart.application.usecase;

import com.goods.cartservice.cart.presentation.dto.response.CartResponse;
import java.util.UUID;

public interface CartSearchUseCase {
    CartResponse findCart(UUID memberId);
}
