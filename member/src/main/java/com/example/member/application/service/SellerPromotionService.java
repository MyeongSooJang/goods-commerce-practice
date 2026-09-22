package com.example.member.application.service;

import com.example.member.domain.repository.MemberRepository;
import com.example.member.domain.repository.SellerRepository;
import com.example.member.infrastructure.messaging.MemberEventPublisher;
import com.example.member.domain.exception.AccountVerificationNotAllowedException;
import com.example.member.domain.exception.AccountVerificationNotFoundException;
import com.example.member.domain.exception.MemberNotFoundException;
import com.example.member.domain.entity.Member;
import com.example.member.domain.entity.Seller;
import com.example.member.infrastructure.crypto.AccountEncryptionService;
import com.example.member.infrastructure.redis.accountverification.AccountVerificationSession;
import com.example.member.infrastructure.redis.accountverification.AccountVerificationSessionStore;
import com.example.member.infrastructure.redis.seller.SellerDraft;
import com.example.member.infrastructure.redis.seller.SellerDraftStore;
import com.example.common.security.auth.enumtype.MemberRole;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerPromotionService {

    private final AccountVerificationSessionStore sessionStore;
    private final SellerDraftStore sellerDraftStore;
    private final SellerRepository sellerRepository;
    private final MemberRepository memberRepository;
    private final AccountEncryptionService accountEncryptionService;
    private final MemberEventPublisher memberEventPort;

    @Transactional
    public void promoteAfterAccountVerified(UUID memberId, String sessionId) {
        AccountVerificationSession session = sessionStore.findSession(sessionId)
                .orElseThrow(AccountVerificationNotFoundException::new);
        if (!session.belongsTo(memberId)) {
            throw new AccountVerificationNotAllowedException("계좌 인증 세션이 현재 회원에게 속하지 않습니다.");
        }
        if (!session.isVerified()) {
            throw new AccountVerificationNotAllowedException("계좌 인증 세션이 아직 검증되지 않았습니다.");
        }

        SellerDraft draft = sellerDraftStore.findDraft(session.getDraftId())
                .orElseThrow(() -> new IllegalStateException("판매자 등록 초안이 존재하지 않습니다."));

        if (sellerRepository.existsByMemberId(memberId)) {
            sellerDraftStore.deleteDraft(draft.getDraftId());
            sellerDraftStore.deleteCurrentDraft(memberId);
            return;
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);

        LocalDateTime now = LocalDateTime.now();
        String accountNumber = accountEncryptionService.decrypt(draft.getEncryptedAccountNumber());
        Seller seller = Seller.create(
                UUID.randomUUID(),
                memberId,
                draft.getBankName(),
                accountNumber,
                now
        );

        sellerRepository.save(seller);
        member.changeRole(MemberRole.SELLER, now);
        memberEventPort.publishSellerPromoted(member, seller);

        sellerDraftStore.deleteDraft(draft.getDraftId());
        sellerDraftStore.deleteCurrentDraft(memberId);
    }
}
