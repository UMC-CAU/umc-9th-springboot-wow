package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}

    public record MemberDetailDTO(
            Long memberId,
            String name,
            String email,
            Gender gender,
            String address,

            List<String> preferFoodNames,

            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record PreferFoodListDTO(
            List<String> preferFoodNames
    ) {}
}
