package com.example.umc9th.global.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReason implements BaseErrorCode {

    private final HttpStatus httpStatus;

    private final String code;

    private final String message;

    @Override
    public HttpStatus getStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static ErrorReason of(String code, String message) {
        return ErrorReason.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .code(code)
                .message(message)
                .build();
    }
}