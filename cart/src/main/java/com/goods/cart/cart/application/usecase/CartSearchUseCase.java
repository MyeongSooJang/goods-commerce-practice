package com.goods.cart.cart.application.usecase;

import com.goods.cart.cart.presentation.dto.response.CartResponse;
import java.util.UUID;

public interface CartSearchUseCase {
    CartResponse findCart(UUID memberId);
}
