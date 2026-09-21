package com.example.member.infrastructure.persistence.jpa;

import com.example.member.domain.entity.MemberOauthAccount;
import com.example.member.domain.enumtype.OAuthProvider;
import com.example.member.domain.repository.MemberOauthAccountRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOauthAccountJpaRepository extends JpaRepository<MemberOauthAccount, UUID>, MemberOauthAccountRepository {

    Optional<MemberOauthAccount> findByProviderAndProviderUserId(OAuthProvider provider, String providerUserId);

    Optional<MemberOauthAccount> findByMemberIdAndProvider(UUID memberId, OAuthProvider provider);

    List<MemberOauthAccount> findAllByMemberIdOrderByCreatedAtDesc(UUID memberId);

    boolean existsByProviderAndProviderUserId(OAuthProvider provider, String providerUserId);

    boolean existsByMemberIdAndProvider(UUID memberId, OAuthProvider provider);

    @Override
    default List<MemberOauthAccount> findAllByMemberId(UUID memberId) {
        return findAllByMemberIdOrderByCreatedAtDesc(memberId);
    }
}
