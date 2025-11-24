package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 새로운 리뷰 추가 API
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO> addReview(
            @PathVariable(name = "storeId") Long storeId, // ★ URL 경로에서 가게 ID를 받음
            @RequestBody @Valid ReviewRequestDTO request) {

        // 1. 하드코딩할 회원 ID 정의 (개발 단계)
        final Long HARDCODED_MEMBER_ID = 1000L;

        // 2. 서비스 호출 및 엔티티 생성
        // storeId는 경로 변수, request는 본문 데이터, memberId는 하드코딩 값
        Review newReview = reviewCommandService.addReview(
                HARDCODED_MEMBER_ID, // 하드코딩된 회원 ID
                storeId,             // URL에서 추출한 가게 ID
                request              // 요청 본문 (rating, content)
        );

        // 3. 응답 DTO로 변환
        ReviewResponseDTO responseDTO = ReviewResponseDTO.toDTO(newReview);

        return ApiResponse.onSuccess(responseDTO);
    }

    @GetMapping("/reviews/search")
    public ApiResponse<List<ReviewResponseDTO>> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        List<ReviewResponseDTO> responseDTOs = reviewQueryService.searchReview(type, query);

        return ApiResponse.onSuccess(responseDTOs);
    }

    @GetMapping("/reviews/me")
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