package com.goods.common.security.auth.dto;

import com.goods.common.security.auth.enumtype.MemberRole;
import java.util.UUID;

public record AuthenticatedMember(
        UUID memberId,
        MemberRole role,
        UUID sessionId
) {
}
