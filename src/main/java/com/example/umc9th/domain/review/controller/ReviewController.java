package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/search")
    public ApiResponse<List<ReviewResponseDTO>> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        List<Review> result = reviewQueryService.searchReview(query, type);

        List<ReviewResponseDTO> responseDTOs = result.stream()
                .map(ReviewResponseDTO::toDTO)
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(responseDTOs);
    }

    @GetMapping("/my-reviews")
    public ApiResponse<List<ReviewResponseDTO>> getMyReviews(
            Principal principal,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String ratingRange) {

        Long memberId;

        if (principal == null) { //비로그인 상태
            memberId = 1000L;
            System.out.println("경고: 인증 정보(Principal)가 없어 memberId 1000L로 임시 처리합니다.");
        } else {
            //로그인 상태
            memberId = Long.parseLong(principal.getName());
        }

        List<Review> reviews = reviewQueryService.getMyReviews(memberId, storeName, ratingRange);

        List<ReviewResponseDTO> responseDTOs = reviews.stream()
                .map(ReviewResponseDTO::toDTO)
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(responseDTOs);
    }
}