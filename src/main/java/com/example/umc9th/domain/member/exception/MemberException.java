package com.example.umc9th.domain.member.exception;

import com.example.umc9th.global.apiPayload.exception.ExceptionCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(ExceptionCode code) {
        super(code);
    }
}