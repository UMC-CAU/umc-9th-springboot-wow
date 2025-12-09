package com.example.umc9th.global.apiPayload;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    // 성공한 경우 (result 포함)
    //유연성과 기본값을 제공하기 위해 두 가지 메서드 작성.
    //1. 기본: GeneralSuccessCode.OK 사용
    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, GeneralSuccessCode.OK.getCode(),
                GeneralSuccessCode.OK.getMessage(), result);
    }

    //2. 커스텀 BaseSuccessCode 사용, 예를 들어 GeneralSuccessCode.CREATED
    public static <T> ApiResponse<T> of(BaseSuccessCode code, T result) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }
    // 실패한 경우 (result 포함)
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}
