package com.example.umc9th.domain.store.enums;

import lombok.Getter;

@Getter
public enum BusinessStatus {

    OPERATING("영업중"),
    CLOSED("휴업중"),
    OUT_OF_HOURS("운영시간 외"),
    DELETED("삭제됨");

    private final String description;

    BusinessStatus(String description) {
        this.description = description;
    }
}
