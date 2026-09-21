package com.example.member.infrastructure.persistence.jpa;

import com.example.member.domain.entity.MemberReport;
import com.example.member.domain.enumtype.ReportStatus;
import com.example.member.domain.repository.MemberReportRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberReportJpaRepository extends JpaRepository<MemberReport, UUID>, MemberReportRepository {

    boolean existsByReporterIdAndReportedMemberIdAndStatus(UUID reporterId, UUID reportedMemberId, ReportStatus status);

    List<MemberReport> findAllByReporterIdOrderByCreatedAtDesc(UUID reporterId);

    List<MemberReport> findAllByReportedMemberIdOrderByCreatedAtDesc(UUID reportedMemberId);

    List<MemberReport> findAllByOrderByCreatedAtDesc();

    @Override
    default boolean existsPendingReport(UUID reporterId, UUID reportedMemberId) {
        return existsByReporterIdAndReportedMemberIdAndStatus(reporterId, reportedMemberId, ReportStatus.PENDING);
    }

    @Override
    default List<MemberReport> findAllByReporterId(UUID reporterId) {
        return findAllByReporterIdOrderByCreatedAtDesc(reporterId);
    }

    @Override
    default List<MemberReport> findAllByReportedMemberId(UUID reportedMemberId) {
        return findAllByReportedMemberIdOrderByCreatedAtDesc(reportedMemberId);
    }

    @Override
    default List<MemberReport> findAll() {
        return findAllByOrderByCreatedAtDesc();
    }
}
