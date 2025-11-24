package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.dto.MemberResDTO;

public interface MemberQueryService {

    MemberResDTO.MemberDetailDTO getMemberDetail(Long memberId);

    boolean isEmailExist(String email);
}