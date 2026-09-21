package com.example.member.infrastructure.messaging;

import com.example.member.domain.entity.Member;
import com.example.member.domain.entity.Seller;
import java.time.LocalDateTime;
import java.util.UUID;

public interface MemberEventPublisher {

    void publishMemberSignedUp(Member member);

    void publishSellerPromoted(Member member, Seller seller);

    void publishAccountVerificationExpired(UUID memberId, String sessionId, String reason);

    void publishAccountVerificationFailed(UUID memberId, String sessionId, String reason);

    void publishMemberOauthLinked(
            UUID memberId,
            String provider,
            String providerUserId,
            String providerEmail,
            String providerNickname,
            LocalDateTime linkedAt
    );
}
