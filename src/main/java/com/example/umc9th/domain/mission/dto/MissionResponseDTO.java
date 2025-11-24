package com.example.umc9th.domain.mission.dto;

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

    // 정적 팩토리 메서드 (향후 MemberMission 엔티티를 받아서 변환할 때 사용)
    // 현재는 MemberMission 엔티티가 없으므로 생략
}