package com.goods.payment.application.dto;

import com.goods.payment.domain.enumtype.ConfirmationType;
import java.util.UUID;

public record EscrowReleaseCommand(
        UUID orderId,
        UUID sellerMemberId,
        ConfirmationType confirmationType
) {
}
