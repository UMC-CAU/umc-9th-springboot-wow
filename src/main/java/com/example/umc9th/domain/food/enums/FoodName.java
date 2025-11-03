package com.example.umc9th.domain.food.enums;

import lombok.Getter;

@Getter
public enum FoodName {
    KOREAN("한식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    WESTERN("양식"),
    ASIAN("아시안"),
    FAST_FOOD("패스트푸드"),
    DESSERT("카페/디저트"),
    ETC("기타");

    // Enum 값이 가질 실제 설명 필드
    private final String description;

    FoodName(String description) {
        this.description = description;
    }

}