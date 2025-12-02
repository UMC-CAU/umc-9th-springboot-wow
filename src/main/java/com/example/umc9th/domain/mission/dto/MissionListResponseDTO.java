package com.example.umc9th.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MissionListResponseDTO {

    private List<MissionDetailResponseDTO> missionList; //개별 미션 dto 목록

    // 페이징 메타데이터
    private Integer listSize;
    private Integer totalPage;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;
}