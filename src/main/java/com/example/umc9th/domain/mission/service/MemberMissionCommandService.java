package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.mapping.MemberMission;

public interface MemberMissionCommandService {

    MemberMission challengeMission(Long memberId, Long missionId);
}