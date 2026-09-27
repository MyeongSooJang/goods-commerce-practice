package com.goods.member.application.dto.result;

import com.goods.member.domain.enumtype.MemberStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record WithdrawMemberResult(
        UUID memberId,
        MemberStatus status,
        LocalDateTime withdrawnAt,
        String message
) {
}
