package com.goods.auction.presentation.dto.response;

public record AuctionSellerBlockingSummaryResponse(
        boolean waiting,
        boolean ongoing,
        boolean pendingPayment
) {
}
