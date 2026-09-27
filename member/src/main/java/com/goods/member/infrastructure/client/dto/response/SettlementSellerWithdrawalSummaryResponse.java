package com.goods.member.infrastructure.client.dto.response;

public record SettlementSellerWithdrawalSummaryResponse(
        boolean hasPendingSettlement,
        boolean hasProcessingSettlement,
        boolean hasPartialSettlementAvailable
) {
}
