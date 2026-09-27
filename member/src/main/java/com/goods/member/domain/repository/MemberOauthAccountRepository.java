package com.goods.member.domain.repository;

import com.goods.member.domain.entity.MemberOauthAccount;
import com.goods.member.domain.enumtype.OAuthProvider;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberOauthAccountRepository {

    MemberOauthAccount save(MemberOauthAccount memberOauthAccount);

    Optional<MemberOauthAccount> findByProviderAndProviderUserId(OAuthProvider provider, String providerUserId);

    Optional<MemberOauthAccount> findByMemberIdAndProvider(UUID memberId, OAuthProvider provider);

    List<MemberOauthAccount> findAllByMemberId(UUID memberId);

    boolean existsByProviderAndProviderUserId(OAuthProvider provider, String providerUserId);

    boolean existsByMemberIdAndProvider(UUID memberId, OAuthProvider provider);

    void delete(MemberOauthAccount memberOauthAccount);
}
