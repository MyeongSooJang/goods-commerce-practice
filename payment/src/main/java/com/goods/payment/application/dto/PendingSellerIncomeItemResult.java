package com.goods.payment.application.dto;

import com.goods.payment.domain.enumtype.EscrowStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 판매자 정산 대기 escrow 목록 조회 결과다.
 */
public record PendingSellerIncomeItemResult(
        UUID escrowId,
        UUID orderId,
        BigDecimal amount,
        EscrowStatus escrowStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
