package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {

    private Long reviewId;
    private BigDecimal rating;
    private String content;

    // 가게 정보 중에서 Lazy Loading 문제를 피하기 위해 필수 필드만 직접 추출
    private Long storeId;
    private String storeName;

    private LocalDateTime createdAt;

    public static ReviewResponseDTO toDTO(Review review) {

        return ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .rating(review.getRating())
                .content(review.getContent())

                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())

                .createdAt(review.getCreatedAt())
                .build();
    }
}