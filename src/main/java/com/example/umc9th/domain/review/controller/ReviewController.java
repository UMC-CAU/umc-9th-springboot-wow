package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    public ReviewController(ReviewQueryService reviewQueryService) {
        this.reviewQueryService = reviewQueryService;
    }

    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        // 실제 검색 로직은 Service 계층에 위임하고, 결과를 받아옵니다.
        List<Review> result = reviewQueryService.searchReview(query, type);

        // 검색 결과를 JSON 형태로 HTTP 응답 본문에 담아 반환합니다.
        return result;
    }

    @GetMapping("/my-reviews")
    public List<Review> getMyReviews(
            Principal principal, // 로그인한 사용자의 인증 정보
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String ratingRange) {

        // 💡 1. null 체크 추가
        if (principal == null) {
            // 임시로 하드코딩된 memberId (예: data.sql에 삽입된 1000번)를 사용
            // 실제 운영 환경에서는 허용되지 않는 방식입니다.
            Long memberId = 1000L;

            // 경고 메시지를 로그에 남겨 비로그인 상태임을 알립니다.
            System.out.println("경고: 인증 정보(Principal)가 없어 memberId 1000L로 임시 처리합니다.");

            return reviewQueryService.getMyReviews(memberId, storeName, ratingRange);
        }

        // 1. 로그인한 사용자 ID(memberId) 추출
        // 실제 애플리케이션에서는 Principal에서 memberId를 Long 타입으로 변환하는 로직이 필요합니다.
        // 여기서는 임시로 Principal의 getName()을 ID 문자열로 사용한다고 가정하고 Long으로 변환합니다.
        Long memberId = Long.parseLong(principal.getName()); // 실제로는 Custom UserDetails에서 ID를 가져와야 합니다.

        // 2. 서비스 계층 호출
        // 모든 필터 조건과 memberId를 서비스 메서드로 전달합니다.
        return reviewQueryService.getMyReviews(memberId, storeName, ratingRange);
    }
}