package com.goods.auction.application.usecase;

import com.goods.auction.domain.enumtype.BidStatus;
import com.goods.auction.presentation.dto.response.BidResponse;
import com.goods.auction.presentation.dto.response.PagedResponse;
import java.util.Map;
import java.util.UUID;

public interface BidSearchUseCase {

    PagedResponse<BidResponse> searchByAuction(UUID auctionId, int page, int size);

    Map<BidStatus, Long> statsBy(UUID auctionId);
}
