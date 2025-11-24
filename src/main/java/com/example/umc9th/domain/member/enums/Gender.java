package com.example.umc9th.domain.member.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE(1, "남성"),
    FEMALE(2, "여성"),
    NONE(3, "선택안함");

    private final Integer value;
    private final String description;

    Gender(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    //MemberConverter에서 호출
    public Integer getValue() {
        return this.value;
    }
}