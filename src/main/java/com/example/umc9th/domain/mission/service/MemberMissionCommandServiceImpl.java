package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.enums.Status;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.example.umc9th.global.apiPayload.exception.ExceptionCode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository; // 새로 추가된 Repository

    @Override
    public MemberMission challengeMission(Long memberId, Long missionId) {

        // 1. 필수 엔티티 조회 (Member, Mission)
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ExceptionCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND)); // Mission 예외 사용

        // 2. 비즈니스 로직 검증

        // 2-1. 미션의 상태가 CHALLENGABLE인지 확인 (Mission 엔티티의 상태)
        if (mission.getStatus() != Status.CHALLENGABLE) {
            throw new MissionException(MissionErrorCode.MISSION_NOT_CHALLENGABLE);
        }

        // 2-2. 해당 회원이 이미 도전 중이거나 완료했는지 확인 (MemberMission 엔티티 체크)
        // ONGOING 또는 COMPLETED 상태라면 도전 불가 (existsByMemberAndMission 사용)
        if (memberMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_CHALLENGED);
        }

        // 3. MemberMission 엔티티 생성 및 저장
        MemberMission newChallenge = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(Status.ONGOING)
                .build();

        return memberMissionRepository.save(newChallenge);
    }
}