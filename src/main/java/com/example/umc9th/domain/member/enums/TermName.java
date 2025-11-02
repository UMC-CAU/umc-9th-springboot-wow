package com.example.umc9th.domain.member.enums;


import lombok.Getter;

public enum TermName {

    //'만 14세 이상'같은 필수 자격 조건은 프론트에서 버튼 비활성화 등으로 처리

    //필수
    SERVICE_TEMRS("서비스 이용약관", true),
    PRIVACY_POLICY("개인 정보 처리 방침", true),

    //선택
    LOCATION_SERVICE_TERMS("위치정보 제공", true),
    MARKETING_CONSENT("마케팅 수신 동의", false);

    @Getter
    private final String description; // 사용자가 보는 약관의 실제 이름
    private final boolean isRequired; // 필수 동의 여부

    TermName(String description, boolean isRequired) {
        this.description = description;
        this.isRequired = isRequired;
    }

    public boolean isRequired(){
        return isRequired;
    }
}
