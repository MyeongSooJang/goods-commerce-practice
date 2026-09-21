package com.example.member.domain.repository;

import com.example.member.domain.entity.MemberReport;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberReportRepository {

    MemberReport save(MemberReport memberReport);

    Optional<MemberReport> findById(UUID reportId);

    boolean existsPendingReport(UUID reporterId, UUID reportedMemberId);

    List<MemberReport> findAllByReporterId(UUID reporterId);

    List<MemberReport> findAllByReportedMemberId(UUID reportedMemberId);

    List<MemberReport> findAll();
}
