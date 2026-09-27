package com.goods.auction.application.usecase;

import com.goods.auction.presentation.dto.request.AuctionCreateRequest;
import com.goods.auction.presentation.dto.response.AuctionResponse;
import java.util.UUID;

public interface AuctionCreateUseCase {

    AuctionResponse create(UUID sellerId, AuctionCreateRequest request);
}
