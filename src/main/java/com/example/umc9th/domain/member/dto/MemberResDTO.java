package com.example.umc9th.domain.member.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}

    //사용자 상세정보 응답 DTO
    public record MemberDetailDTO(
            // 기본 정보
            Long memberId,
            String name,
            String nickname,
            String email,
            Integer gender, // 또는 String gender
            Integer age,
            String address,

            // 선호 음식 목록 (이름만 반환)
            List<String> preferFoodNames,

            // 기타 정보
            LocalDateTime createdAt
            // LocalDateTime updatedAt
    ) {}

    public record PreferFoodListDTO(
            List<String> preferFoodNames
    ) {}
}
