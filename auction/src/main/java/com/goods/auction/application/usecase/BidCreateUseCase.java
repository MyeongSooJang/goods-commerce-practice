package com.goods.auction.application.usecase;

import com.goods.auction.presentation.dto.request.BidPlaceRequest;
import com.goods.auction.presentation.dto.response.BidResponse;
import java.util.UUID;

public interface BidCreateUseCase {

    BidResponse place(UUID auctionId, UUID bidderId, BidPlaceRequest request);
}
