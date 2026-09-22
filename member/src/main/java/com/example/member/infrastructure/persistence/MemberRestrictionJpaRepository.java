package com.example.member.infrastructure.persistence;

import com.example.member.domain.entity.MemberRestriction;
import com.example.member.domain.enumtype.RestrictionType;
import com.example.member.domain.repository.MemberRestrictionRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRestrictionJpaRepository extends JpaRepository<MemberRestriction, UUID>, MemberRestrictionRepository {

    boolean existsByMemberIdAndRestrictionTypeAndActiveTrueAndEndAtAfter(
            UUID memberId,
            RestrictionType restrictionType,
            LocalDateTime now
    );

    Optional<MemberRestriction> findFirstByMemberIdAndRestrictionTypeAndActiveTrueAndEndAtAfterOrderByEndAtDesc(
            UUID memberId,
            RestrictionType restrictionType,
            LocalDateTime now
    );

    List<MemberRestriction> findAllByOrderByCreatedAtDesc();

    List<MemberRestriction> findAllByMemberIdOrderByCreatedAtDesc(UUID memberId);

    @Override
    default boolean existsActiveRestriction(UUID memberId, RestrictionType restrictionType, LocalDateTime now) {
        return existsByMemberIdAndRestrictionTypeAndActiveTrueAndEndAtAfter(memberId, restrictionType, now);
    }

    @Override
    default Optional<MemberRestriction> findActiveRestriction(UUID memberId, RestrictionType restrictionType, LocalDateTime now) {
        return findFirstByMemberIdAndRestrictionTypeAndActiveTrueAndEndAtAfterOrderByEndAtDesc(memberId, restrictionType, now);
    }

    @Override
    default List<MemberRestriction> findAll() {
        return findAllByOrderByCreatedAtDesc();
    }

    @Override
    default List<MemberRestriction> findAllByMemberId(UUID memberId) {
        return findAllByMemberIdOrderByCreatedAtDesc(memberId);
    }
}
