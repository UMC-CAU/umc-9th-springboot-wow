package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    // 200 OK: 요청 성공
    OK(HttpStatus.OK,
            "COMMON200",
            "요청에 성공했습니다."),

    // 201 Created: 리소스 생성 성공
    CREATED(HttpStatus.CREATED,
            "COMMON201",
            "리소스 생성에 성공했습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}