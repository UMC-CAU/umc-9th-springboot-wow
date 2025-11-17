package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            Long locationId,
            String addressDetail,
            String email,
            String phoneNumber,
            List<Long> preferFoodsIds
    ) {}
}