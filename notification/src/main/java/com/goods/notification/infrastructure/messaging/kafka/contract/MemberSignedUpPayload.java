package com.goods.notification.infrastructure.messaging.kafka.contract;

import java.util.UUID;

public record MemberSignedUpPayload(
        UUID memberId,
        String email
) {
}
