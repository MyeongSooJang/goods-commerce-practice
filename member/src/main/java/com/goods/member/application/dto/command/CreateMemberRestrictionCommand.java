package com.goods.member.application.dto.command;

import com.goods.member.domain.enumtype.RestrictionType;
import java.util.UUID;

public record CreateMemberRestrictionCommand(
        UUID memberId,
        String reason,
        RestrictionType restrictionType,
        Integer durationHours
) {
}
