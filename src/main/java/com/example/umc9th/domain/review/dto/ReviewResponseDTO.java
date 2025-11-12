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

    // 1. Review 자체 정보
    private Long reviewId;
    private BigDecimal rating;
    private String content;

    // 2. 가게 정보 (Lazy Loading 문제를 피하기 위해 필수 필드만 직접 추출)
    private Long storeId;
    private String storeName;

    // 3. 시간 정보
    private LocalDateTime createdAt;

    /**
     * Review 엔티티를 DTO로 변환하는 정적 팩토리 메서드
     * 이 메서드를 통해 Controller에서 List<Review>를 List<ReviewResponseDTO>로 변환합니다.
     */
    public static ReviewResponseDTO toDTO(Review review) {
        // Review 엔티티의 연관 관계 필드(store)에 접근하여 필요한 정보만 추출합니다.
        // 이때, Lazy Loading이 발생하지만, DTO로 변환 후 반환하므로 직렬화 오류는 방지됩니다.

        return ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .rating(review.getRating())
                .content(review.getContent())

                // 연관 엔티티(Store)에서 정보 추출
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())

                .createdAt(review.getCreatedAt())
                .build();
    }
}