package com.example.member.infrastructure.persistence.jpa;

import com.example.member.domain.entity.EmailVerification;
import com.example.member.domain.enumtype.EmailVerificationPurpose;
import com.example.member.domain.enumtype.EmailVerificationStatus;
import com.example.member.domain.repository.EmailVerificationRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationJpaRepository extends JpaRepository<EmailVerification, UUID>, EmailVerificationRepository {

    Optional<EmailVerification> findByToken(String token);

    List<EmailVerification> findAllByMemberIdAndPurposeAndStatus(
            UUID memberId,
            EmailVerificationPurpose purpose,
            EmailVerificationStatus status
    );

    List<EmailVerification> findAllByEmailAndPurposeAndStatus(
            String email,
            EmailVerificationPurpose purpose,
            EmailVerificationStatus status
    );

    @Override
    default List<EmailVerification> findPendingByMemberIdAndPurpose(UUID memberId, EmailVerificationPurpose purpose) {
        return findAllByMemberIdAndPurposeAndStatus(memberId, purpose, EmailVerificationStatus.PENDING);
    }

    @Override
    default List<EmailVerification> findPendingByEmailAndPurpose(String email, EmailVerificationPurpose purpose) {
        return findAllByEmailAndPurposeAndStatus(email, purpose, EmailVerificationStatus.PENDING);
    }
}
