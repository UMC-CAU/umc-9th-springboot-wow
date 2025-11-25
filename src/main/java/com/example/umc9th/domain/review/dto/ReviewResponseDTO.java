package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponseDTO {

    private Long review_id;
    private Long member_id;
    private Long store_id;
    private BigDecimal rating;
    private String content;
    private LocalDateTime created_at;

    public static ReviewResponseDTO toDTO(Review review) {
        return ReviewResponseDTO.builder()
                .review_id(review.getId())
                .member_id(review.getMember().getId())
                .store_id(review.getStore().getId())
                .rating(review.getRating())
                .content(review.getContent())
                .created_at(review.getCreatedAt())
                .build();
    }
}