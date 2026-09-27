package com.goods.member.presentation.dto;

import com.goods.member.domain.enumtype.RestrictionType;

public record ReviewMemberReportRequest(
        String reviewComment,
        RestrictionType restrictionType,
        Integer durationHours
) {
}

