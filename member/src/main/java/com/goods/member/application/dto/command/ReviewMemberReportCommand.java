package com.goods.member.application.dto.command;

import com.goods.member.domain.enumtype.RestrictionType;

public record ReviewMemberReportCommand(
        String reviewComment,
        RestrictionType restrictionType,
        Integer durationHours
) {
}
