package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.Status;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("SELECT mm FROM MemberMission mm JOIN mm.mission m WHERE mm.member.id = :memberId AND m.status IN :statusList")
    Slice<MemberMission> findAllByMemberIdAndStatusIn(
            @Param("memberId") Long memberId,
            @Param("statusList") List<Status> statusList,
            Pageable pageable
    );
}
