package com.example.umc9th.global.apiPayload.exception;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final ExceptionCode code;

    /**
     * 워크북과 달리, DTO의 BaseErrorCode 대신 ExceptionCode를 직접 받도록 변경함.
     * (ExceptionCode로 BaseErrorCode를 구현해서 이렇게 변경해도 괜찮은가요??)
     */
    public GeneralException(ExceptionCode code) {
        super(code.getMessage());

        this.code = code;
    }

}