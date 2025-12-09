package com.example.umc9th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewListResponseDTO {

    private List<ReviewResponseDTO> reviewList; // 개별 리뷰 DTO 목록

    private Integer listSize;      // 현재 페이지의 요소 개수
    private Integer totalPage;     // 전체 페이지 수
    private Long totalElements;    // 전체 요소 개수
    private Boolean isFirst;
    private Boolean isLast;
}