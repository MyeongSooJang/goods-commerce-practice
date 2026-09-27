package com.goods.member.application.dto.result;

import com.goods.member.domain.enumtype.MemberStatus;
import com.goods.common.security.auth.enumtype.MemberRole;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateMemberResult(
        UUID memberId,
        String nickname,
        String profileImageUrl,
        MemberRole role,
        MemberStatus status,
        LocalDateTime createdAt
) {
}
