package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    public ReviewController(ReviewQueryService reviewQueryService) {
        this.reviewQueryService = reviewQueryService;
    }

    @GetMapping("/reviews/search")
    // 💡 필수 수정: 반환 타입을 List<ReviewResponseDTO>로 변경
    public List<ReviewResponseDTO> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        List<Review> result = reviewQueryService.searchReview(query, type);

        // 검색 결과를 DTO로 변환하여 반환
        return result.stream()
                .map(ReviewResponseDTO::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/my-reviews")
    public List<ReviewResponseDTO> getMyReviews(
            Principal principal,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String ratingRange) {

        // 💡 memberId 초기화
        Long memberId;

        if (principal == null) {
            // 비로그인 상태일 때 임시 ID 사용
            memberId = 1000L;
            System.out.println("경고: 인증 정보(Principal)가 없어 memberId 1000L로 임시 처리합니다.");
        } else {
            // 💡 로그인 상태일 때 Principal에서 실제 ID 추출
            // 실제 애플리케이션에서는 예외 처리(NumberFormatException) 및 Member ID 검증 필요
            memberId = Long.parseLong(principal.getName());
        }

        // 서비스 계층 호출
        List<Review> reviews = reviewQueryService.getMyReviews(memberId, storeName, ratingRange);

        // DTO로 변환하여 반환
        return reviews.stream()
                .map(ReviewResponseDTO::toDTO)
                .collect(Collectors.toList());
    }
}