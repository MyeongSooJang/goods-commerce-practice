package com.goods.member.application.dto.result;

import com.goods.member.domain.enumtype.MemberStatus;
import java.util.UUID;

public record EmailVerificationConfirmResult(
        UUID memberId,
        String email,
        MemberStatus status,
        String autoLoginToken,
        long autoLoginTokenExpiresInSeconds
) {
}
