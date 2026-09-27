package com.goods.cartservice.cart.application.service;

import com.goods.cartservice.cart.application.usecase.CartSearchUseCase;
import com.goods.cartservice.cart.domain.entity.Cart;
import com.goods.cartservice.cart.domain.repository.CartRepository;
import com.goods.cartservice.cart.presentation.dto.response.CartItemResponse;
import com.goods.cartservice.cart.presentation.dto.response.CartResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartSearchService implements CartSearchUseCase {

    private final CartRepository cartRepository;

    @Override
    public CartResponse findCart(UUID memberId) {
        List<Cart> cartItems = cartRepository.findAllByMemberId(memberId);

        List<CartItemResponse> itemResponses = cartItems.stream()
            .map(CartItemResponse::from)
            .collect(Collectors.toList());

        return CartResponse.of(memberId, itemResponses);
    }
}
