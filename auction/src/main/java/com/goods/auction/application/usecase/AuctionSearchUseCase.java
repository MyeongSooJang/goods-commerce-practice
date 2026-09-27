package com.goods.auction.application.usecase;

import com.goods.auction.domain.enumtype.AuctionStatus;
import com.goods.auction.presentation.dto.response.AuctionSellerBlockingSummaryResponse;
import com.goods.auction.presentation.dto.response.AuctionResponse;
import com.goods.auction.presentation.dto.response.PagedResponse;
import java.util.UUID;

public interface AuctionSearchUseCase {

    AuctionResponse findById(UUID auctionId);

    PagedResponse<AuctionResponse> search(AuctionStatus status, int page, int size);

    AuctionSellerBlockingSummaryResponse getSellerBlockingSummary(UUID sellerId);
}
