package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    // HTTP 400: 평점 범위 오류 (Validation에서 처리되지만, 비즈니스 로직 예외로도 정의 가능)
    RATING_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, "REVIEW4002", "평점은 0.0점에서 5.0점 사이여야 합니다."),

    // HTTP 404: 리뷰를 찾을 수 없음 (향후 조회/수정/삭제 시 사용)
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4003", "해당 리뷰를 찾을 수 없습니다.");

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