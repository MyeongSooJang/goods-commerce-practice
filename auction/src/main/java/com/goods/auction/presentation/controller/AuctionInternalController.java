package com.goods.auction.presentation.controller;

import com.goods.auction.application.usecase.AuctionSearchUseCase;
import com.goods.auction.presentation.dto.response.ApiResponse;
import com.goods.auction.presentation.dto.response.AuctionSellerBlockingSummaryResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/auctions")
@RequiredArgsConstructor
public class AuctionInternalController {

    private final AuctionSearchUseCase auctionSearchUseCase;

    @GetMapping("/sellers/{sellerId}/blocking-summary")
    public ResponseEntity<ApiResponse<AuctionSellerBlockingSummaryResponse>> getSellerBlockingSummary(
            @PathVariable UUID sellerId
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                auctionSearchUseCase.getSellerBlockingSummary(sellerId)
        ));
    }
}
