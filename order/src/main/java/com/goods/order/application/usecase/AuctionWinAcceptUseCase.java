package com.goods.order.application.usecase;

import com.goods.order.presentation.dto.request.AuctionWinAcceptRequest;
import com.goods.order.presentation.dto.response.OrderCreateResponse;

import java.util.UUID;

public interface AuctionWinAcceptUseCase {

    OrderCreateResponse acceptWinByDeposit(UUID memberId, AuctionWinAcceptRequest request);

    OrderCreateResponse acceptWinByPg(UUID memberId, AuctionWinAcceptRequest request);
}
