package com.example.umc9th.domain.inquiry.enums;

import lombok.*;

@Getter
public enum InquiryType {

    SERVICE_USAGE("서비스 이용 관련"),
    MISSION_ERROR("미션 수행/적립 오류"),
    STORE_INFO("가게 정보 관련"),
    TECHNICAL_BUG("기술적 오류/버그 관련"),
    ACCOUNT_INFO("개인정보 관련"),
    ETC("기타");

    private final String description;

    InquiryType(String description) {
        this.description = description;
    }


}
