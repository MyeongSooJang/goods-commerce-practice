package com.goods.settlement.application.dto;

import com.goods.settlement.domain.enumtype.SettlementStatus;
import com.goods.settlement.domain.enumtype.SettlementType;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * 판매자 부분 정산 생성 결과다.
 */
public record PartialSettlementCreateResult(
        UUID settlementId,
        UUID sellerId,
        SettlementType settlementType,
        SettlementStatus settlementStatus,
        int settlementItemCount,
        BigDecimal totalSalesAmount,
        BigDecimal feeAmount,
        BigDecimal finalSettlementAmount
) {
}
