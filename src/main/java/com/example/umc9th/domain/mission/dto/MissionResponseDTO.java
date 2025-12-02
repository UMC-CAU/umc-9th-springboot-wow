package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MissionResponseDTO {

    private Long member_mission_id;
    private Long mission_id;
    private Long member_id;
    private String status;
    private LocalDateTime created_at;

    public static MissionResponseDTO toDTO(MemberMission memberMission) {
        return MissionResponseDTO.builder()
                .member_mission_id(memberMission.getId())
                .mission_id(memberMission.getMission().getId())
                .member_id(memberMission.getMember().getId())
                .status(memberMission.getStatus().name())
                .created_at(memberMission.getCreatedAt())
                .build();
    }
}

