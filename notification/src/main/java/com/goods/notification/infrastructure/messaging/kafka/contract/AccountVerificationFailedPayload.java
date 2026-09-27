package com.goods.notification.infrastructure.messaging.kafka.contract;

import java.util.UUID;

public record AccountVerificationFailedPayload(
        UUID memberId,
        String sessionId,
        String reason
) {
}
