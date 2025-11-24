package com.example.umc9th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReviewRequestDTO {

    //store_id는 경로로 받기 때문에 필요 x

    @NotNull(message = "별점은 필수입니다.")
    @DecimalMin(value = "0.0", inclusive = true, message = "별점은 0.0점 이상이어야 합니다.")
    @DecimalMax(value = "5.0", inclusive = true, message = "별점은 5.0점 이하여야 합니다.")
    private BigDecimal rating;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;
}