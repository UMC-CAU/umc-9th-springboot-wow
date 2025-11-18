package com.example.umc9th.global.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    // ----------------------------------------------------------------------
    // 1. 공통 오류 (COMMON)
    // ----------------------------------------------------------------------

    // HTTP 500: 내부 서버 오류
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),

    // HTTP 400: 잘못된 요청 (Validation 오류 등)
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다. 요청 형식을 확인해주세요."),

    // HTTP 401: 인증 실패
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다. 토큰을 확인해주세요."),

    // HTTP 403: 접근 금지 (권한 없음)
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "접근 권한이 없습니다."),

    // ----------------------------------------------------------------------
    // 2. 회원 관련 오류 (MEMBER, 1xxx)
    // ----------------------------------------------------------------------

    // HTTP 404: 리소스 없음
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "해당 사용자를 찾을 수 없습니다."),

    // HTTP 400: 유효성 검사 실패 (중복 등)
    MEMBER_EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "이미 가입된 이메일 주소입니다."),

    // HTTP 400: 유효성 검사 실패 (유효하지 않은 지역 ID 등)
    LOCATION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4003", "유효하지 않은 지역 ID 입니다."),

    // ----------------------------------------------------------------------
    // 3. 미션 관련 오류 (MISSION, 2xxx)
    // ----------------------------------------------------------------------

    // HTTP 404: 리소스 없음
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "존재하지 않는 미션입니다."),

    // HTTP 400: 잘못된 상태 전이
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION4002", "이미 완료된 미션입니다."),

    // ... 기타 도메인별 오류를 추가 정의
    ; // ENUM 정의 끝

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
