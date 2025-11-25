package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.location.entity.Location;
import com.example.umc9th.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate dateOfBirth,
            Location locationId,
            String addressDetail,
            String email,
            String phoneNumber,
            List<Long> preferCategory
    ) {}
}