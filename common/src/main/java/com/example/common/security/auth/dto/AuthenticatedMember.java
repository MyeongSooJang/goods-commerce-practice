package com.example.common.security.auth.dto;

import com.example.common.security.auth.enumtype.MemberRole;
import java.util.UUID;

public record AuthenticatedMember(
        UUID memberId,
        MemberRole role,
        UUID sessionId
) {
}
