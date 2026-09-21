package com.example.member.application.service;

import com.example.member.application.dto.command.AuthSessionMetadata;
import com.example.member.application.dto.result.AuthTokenResult;
import com.example.member.application.dto.result.EmailVerificationAutoLoginTokenResult;
import com.example.member.domain.repository.MemberRepository;
import com.example.member.infrastructure.redis.emailverification.RedisEmailVerificationAutoLoginTokenStore;
import com.example.member.domain.exception.InvalidEmailVerificationAutoLoginTokenException;
import com.example.member.domain.entity.Member;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.member.infrastructure.redis.emailverification.EmailVerificationAutoLoginToken;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailVerificationAutoLoginService {

    private final RedisEmailVerificationAutoLoginTokenStore emailVerificationAutoLoginTokenStore;
    private final MemberRepository memberRepository;
    private final AuthService authService;
    private final com.example.member.config.EmailVerificationProperties emailVerificationProperties;

    @Transactional
    public EmailVerificationAutoLoginTokenResult issueToken(Member member) {
        UUID memberId = member == null ? null : member.getMemberId();
        if (memberId == null) {
            throw new IllegalArgumentException("member는 필수입니다.");
        }

        String token = UUID.randomUUID().toString();
        emailVerificationAutoLoginTokenStore.create(
                new EmailVerificationAutoLoginToken(token, memberId, Instant.now()),
                emailVerificationProperties.autoLoginExpiration()
        );

        return new EmailVerificationAutoLoginTokenResult(
                token,
                emailVerificationProperties.autoLoginExpiration().toSeconds()
        );
    }

    @Transactional
    public AuthTokenResult authenticate(String autoLoginToken, AuthSessionMetadata metadata) {
        String normalizedToken = normalizeRequired(autoLoginToken, "autoLoginToken");
        EmailVerificationAutoLoginToken storedToken = emailVerificationAutoLoginTokenStore.consume(normalizedToken)
                .orElseThrow(InvalidEmailVerificationAutoLoginTokenException::new);

        Member member = memberRepository.findById(storedToken.memberId())
                .orElseThrow(InvalidEmailVerificationAutoLoginTokenException::new);

        return authService.login(member, metadata == null ? AuthSessionMetadata.empty() : metadata);
    }

    private String normalizeRequired(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "는 필수입니다.");
        }
        return value.trim();
    }
}
