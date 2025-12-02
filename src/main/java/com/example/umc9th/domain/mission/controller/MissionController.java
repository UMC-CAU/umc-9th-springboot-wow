package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionListResponseDTO;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.service.MemberMissionCommandService;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.validation.annotation.PageCheck;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mission", description = "미션 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MemberMissionCommandService memberMissionCommandService;
    private final MissionQueryService missionQueryService;

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

    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionListResponseDTO> getStoreMissions(
            @PathVariable(name = "storeId") Long storeId,
            @Parameter(description = "조회할 페이지 번호 (1부터 시작, 10개씩)", required = true)
            @RequestParam(name = "page") @PageCheck Integer page) {

        MissionListResponseDTO responseDTO = missionQueryService.getMissionListByStore(storeId, page);

        return ApiResponse.onSuccess(responseDTO);
    }
}