package com.goods.notification.infrastructure.messaging.kafka.contract;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberOauthLinkedPayload(
        UUID memberId,
        String provider,
        String providerUserId,
        String providerEmail,
        String providerNickname,
        LocalDateTime linkedAt
) {
}
