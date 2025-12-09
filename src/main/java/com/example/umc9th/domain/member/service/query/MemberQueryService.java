package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import jakarta.validation.Valid;

public interface MemberQueryService {

    MemberResDTO.LoginDTO login(
            @Valid MemberReqDTO.LoginDTO dto
    );}