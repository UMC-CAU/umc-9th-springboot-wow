package com.example.umc9th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

// BaseErrorCode와 동일한 역할을 수행하지만 성공 케이스에 사용됩니다.
public interface BaseSuccessCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();

}