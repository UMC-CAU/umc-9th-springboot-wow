package com.example.umc9th.domain.review.controller;

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
        List<ReviewResponseDTO> responseDTOs = reviewQueryService.searchReview(type, query);

        return ApiResponse.onSuccess(responseDTOs);
    }

    @GetMapping("/my-reviews")
    public ApiResponse<List<ReviewResponseDTO>> getMyReviews(
            Principal principal,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String ratingRange) {

        long memberId;

        if (principal == null) { //비로그인 상태
            memberId = 1000L;
            System.out.println("경고: 인증 정보(Principal)가 없어 memberId 1000L로 임시 처리합니다.");
        } else {
            //로그인 상태
            memberId = Long.parseLong(principal.getName());
        }

        List<ReviewResponseDTO> responseDTOs = reviewQueryService.getMyReviews(memberId, storeName, ratingRange);

        return ApiResponse.onSuccess(responseDTOs);
    }
}