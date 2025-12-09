package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.global.auth.enums.Role;

import java.util.List;

public class MemberConverter {

    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .dateOfBirth(dto.dateOfBirth())
                .locationName(dto.locationName())
                .addressDetail(dto.addressDetail())
                .phoneNumber(dto.phoneNumber())
                .gender(dto.gender())
                .missionNum(0)
                .totalPoint(0)
                .build();
    }

    public static MemberResDTO.MemberDetailDTO toDetailDTO(
            Member member,
            List<String> preferFoodNames
    ){
        return new MemberResDTO.MemberDetailDTO(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getGender(),
                member.getAddressDetail(),

                preferFoodNames,

                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }

    public static MemberResDTO.LoginDTO toLoginDTO(
            Member member,
            String accessToken
    ) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .accessToken(accessToken)
                .build();
    }
}