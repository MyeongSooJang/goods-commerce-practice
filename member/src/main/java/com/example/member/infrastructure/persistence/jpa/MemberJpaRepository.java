package com.example.member.infrastructure.persistence.jpa;

import com.example.member.domain.entity.Member;
import com.example.member.domain.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, UUID>, MemberRepository {

    boolean existsByEmail(String email);

    boolean existsByEmailAndMemberIdNot(String email, UUID memberId);

    Optional<Member> findByEmail(String email);

    @Override
    default List<Member> findAllByIds(Iterable<UUID> memberIds) {
        return findAllById(memberIds);
    }
}
