package com.example.member.application.service;

import com.example.member.application.dto.command.AccountVerificationCreateCommand;
import com.example.member.application.dto.command.SellerRegisterCommand;
import com.example.member.application.dto.result.AccountVerificationSendResult;
import com.example.member.application.dto.result.SellerResult;
import com.example.member.domain.repository.MemberRepository;
import com.example.member.domain.repository.SellerRepository;
import com.example.member.domain.exception.MemberNotFoundException;
import com.example.member.domain.exception.SellerAlreadyRegisteredException;
import com.example.member.domain.exception.SellerNotFoundException;
import com.example.member.domain.entity.Member;
import com.example.member.domain.entity.Seller;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerPersistencePort;
    private final MemberRepository memberPersistencePort;
    private final AccountVerificationService accountVerificationService;

    @Transactional
    public AccountVerificationSendResult registerSeller(UUID memberId, SellerRegisterCommand command) {
        validateRegisterCommand(command);
        getMember(memberId);

        if (sellerPersistencePort.existsByMemberId(memberId)) {
            throw new SellerAlreadyRegisteredException();
        }

        return accountVerificationService.createAccountVerification(
                memberId,
                new AccountVerificationCreateCommand(
                        normalizeRequired(command.bankName(), "bankName"),
                        normalizeRequired(command.account(), "account")
                )
        );
    }

    public SellerResult getCurrentSeller(UUID memberId) {
        getMember(memberId);
        Seller seller = sellerPersistencePort.findByMemberId(memberId)
                .orElseThrow(SellerNotFoundException::new);

        return new SellerResult(
                seller.getSellerId(),
                seller.getMemberId(),
                seller.getBankName(),
                seller.getAccount(),
                seller.getApprovedAt()
        );
    }

    private void validateRegisterCommand(SellerRegisterCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("판매자 등록 요청은 필수입니다.");
        }
    }

    private Member getMember(UUID memberId) {
        return memberPersistencePort.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    private String normalizeRequired(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다.");
        }
        return value.trim();
    }
}

