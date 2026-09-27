package com.goods.order.presentation.dto.response;

import com.goods.order.domain.enumtype.InspectionResult;
import com.goods.order.domain.enumtype.ReturnRequestStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ReturnInspectResponse(
        UUID returnRequestId,
        ReturnRequestStatus status,
        InspectionResult inspectionResult,
        BigDecimal refundedAmount,
        LocalDateTime processedAt
) {
}
