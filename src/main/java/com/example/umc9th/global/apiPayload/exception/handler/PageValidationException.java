package com.example.umc9th.global.apiPayload.exception.handler;

import com.example.umc9th.global.apiPayload.code.ErrorReason;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class PageValidationException extends GeneralException {

    public PageValidationException(ErrorReason errorReason) {
        super(errorReason);
    }
}