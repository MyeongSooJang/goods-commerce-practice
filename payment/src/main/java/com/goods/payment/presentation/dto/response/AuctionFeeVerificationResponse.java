package com.goods.payment.presentation.dto.response;

import com.goods.payment.application.dto.AuctionDepositResult;
import java.math.BigDecimal;
import java.util.UUID;

public record AuctionFeeVerificationResponse(
        UUID auctionId
) {

    public static AuctionFeeVerificationResponse success(AuctionDepositResult result) {
        return new AuctionFeeVerificationResponse(
                result.auctionId()
        );
    }
}
