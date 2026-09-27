package com.goods.member.application.dto.result;

import com.goods.member.domain.enumtype.ReportStatus;
import com.goods.member.domain.enumtype.ReportType;
import java.time.LocalDateTime;
import java.util.UUID;

public record MemberReportResult(
        UUID reportId,
        UUID reporterId,
        String reporterNickname,
        UUID reportedMemberId,
        String reportedMemberNickname,
        String reason,
        ReportType reportType,
        ReportStatus status,
        String reviewComment,
        UUID reviewedBy,
        String reviewedByNickname,
        LocalDateTime reviewedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
