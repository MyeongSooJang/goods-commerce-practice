package com.goods.member.infrastructure.messaging;

import com.goods.member.application.event.AccountVerificationExpiredEvent;
import com.goods.member.application.event.AccountVerificationFailedEvent;
import com.goods.member.application.event.MemberOauthLinkedEvent;
import com.goods.member.application.event.MemberSignedUpEvent;
import com.goods.member.application.event.SellerPromotedEvent;
import com.goods.member.domain.entity.Member;
import com.goods.member.domain.entity.Seller;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringMemberEventPublisher implements MemberEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishMemberSignedUp(Member member) {
        applicationEventPublisher.publishEvent(new MemberSignedUpEvent(
                member.getMemberId(),
                member.getEmail()
        ));
    }

    @Override
    public void publishSellerPromoted(Member member, Seller seller) {
        applicationEventPublisher.publishEvent(new SellerPromotedEvent(
                member.getMemberId(),
                seller.getSellerId(),
                seller.getBankName()
        ));
    }

    @Override
    public void publishAccountVerificationExpired(UUID memberId, String sessionId, String reason) {
        applicationEventPublisher.publishEvent(new AccountVerificationExpiredEvent(
                memberId,
                sessionId,
                reason
        ));
    }

    @Override
    public void publishAccountVerificationFailed(UUID memberId, String sessionId, String reason) {
        applicationEventPublisher.publishEvent(new AccountVerificationFailedEvent(
                memberId,
                sessionId,
                reason
        ));
    }

    @Override
    public void publishMemberOauthLinked(
            UUID memberId,
            String provider,
            String providerUserId,
            String providerEmail,
            String providerNickname,
            LocalDateTime linkedAt
    ) {
        applicationEventPublisher.publishEvent(new MemberOauthLinkedEvent(
                memberId,
                provider,
                providerUserId,
                providerEmail,
                providerNickname,
                linkedAt
        ));
    }
}
