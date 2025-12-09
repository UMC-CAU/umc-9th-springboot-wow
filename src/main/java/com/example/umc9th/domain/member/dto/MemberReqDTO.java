package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.location.entity.Location;
import com.example.umc9th.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email,
            @NotBlank
            String password,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate dateOfBirth,
            @NotNull
            String locationName,
            @NotNull
            String addressDetail,
            @NotNull
            String phoneNumber,
            List<Long> preferCategory,
            Integer missionNum,
            Integer totalPoint
    ) {}
}