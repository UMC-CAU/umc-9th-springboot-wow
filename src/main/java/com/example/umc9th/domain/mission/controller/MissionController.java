package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.service.MemberMissionCommandService;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.global.apiPayload.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/missions/{missionId}/challenge")
    public ApiResponse<MissionResponseDTO> challengeMission(
            @PathVariable(name = "missionId") Long missionId) {

        final Long HARDCODED_MEMBER_ID = 1000L;

        MemberMission newChallenge = memberMissionCommandService.challengeMission(
                HARDCODED_MEMBER_ID,
                missionId
        );

        MissionResponseDTO responseDTO = MissionResponseDTO.toDTO(newChallenge);

        return ApiResponse.of(MissionSuccessCode.MISSION_CHALLENGE_SUCCESS, responseDTO);
    }
}