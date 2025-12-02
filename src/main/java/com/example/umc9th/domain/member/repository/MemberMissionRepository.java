package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    boolean existsByMemberAndMission(Member member, Mission mission);

    Page<MemberMission> findAllByMemberIdAndStatus(
            Long memberId,
            Status status,
            Pageable pageable
    );
}
