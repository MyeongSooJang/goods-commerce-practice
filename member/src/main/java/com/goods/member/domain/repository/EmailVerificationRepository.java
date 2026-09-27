package com.goods.member.domain.repository;

import com.goods.member.domain.entity.EmailVerification;
import com.goods.member.domain.enumtype.EmailVerificationPurpose;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmailVerificationRepository {

    EmailVerification save(EmailVerification emailVerification);

    Optional<EmailVerification> findById(UUID verificationId);

    Optional<EmailVerification> findByToken(String token);

    List<EmailVerification> findPendingByMemberIdAndPurpose(UUID memberId, EmailVerificationPurpose purpose);

    List<EmailVerification> findPendingByEmailAndPurpose(String email, EmailVerificationPurpose purpose);
}
