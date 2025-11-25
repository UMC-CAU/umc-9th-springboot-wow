package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {


    // HTTP 404: 미션을 찾을 수 없음
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "존재하지 않는 미션입니다."),

    // HTTP 409: 상태 충돌 - 이미 도전 중이거나 완료된 미션
    MISSION_ALREADY_CHALLENGED(HttpStatus.CONFLICT, "MISSION4002", "이미 도전 중이거나 완료된 미션입니다."),

    // HTTP 400: 미션 상태 오류 - 도전 가능한 상태가 아님 (CHALLENGABLE이 아님)
    MISSION_NOT_CHALLENGABLE(HttpStatus.BAD_REQUEST, "MISSION4003", "미션이 도전 가능한 상태가 아닙니다."),

    // HTTP 400: 만료일 오류
    MISSION_EXPIRED(HttpStatus.BAD_REQUEST, "MISSION4004", "이미 마감일이 지난 미션입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getStatus() { return httpStatus; }

    @Override
    public String getCode() { return code; }

    @Override
    public String getMessage() { return message; }
}