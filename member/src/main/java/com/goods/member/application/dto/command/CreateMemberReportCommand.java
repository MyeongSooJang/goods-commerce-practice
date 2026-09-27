package com.goods.member.application.dto.command;

import com.goods.member.domain.enumtype.ReportType;
import java.util.UUID;

public record CreateMemberReportCommand(
        UUID reportedMemberId,
        String reason,
        ReportType reportType
) {
}
