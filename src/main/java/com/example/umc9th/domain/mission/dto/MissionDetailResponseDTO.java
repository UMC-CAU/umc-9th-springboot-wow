package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.entity.Mission;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MissionDetailResponseDTO {

    private Long missionId;
    private Long storeId;
    private Integer minimumOrderAmount;
    private LocalDate dueDate;
    private Integer point;
    private String status;

    public static MissionDetailResponseDTO toDTO(Mission mission) {
        return MissionDetailResponseDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .minimumOrderAmount(mission.getMinimumOrderAmount())
                .dueDate(mission.getDueDate())
                .point(mission.getPoint())
                .status(mission.getStatus().name())
                .build();
    }
}