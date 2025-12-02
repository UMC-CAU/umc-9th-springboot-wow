package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.umc9th.domain.review.dto.ReviewListResponseDTO;
import com.example.umc9th.global.validation.annotation.PageCheck;

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
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid ReviewRequestDTO request) {

        // 1. 하드코딩할 회원 ID 정의 (개발 단계)
        final Long HARDCODED_MEMBER_ID = 1000L;

        // 2. 서비스 호출 및 엔티티 생성
        Review newReview = reviewCommandService.addReview(
                HARDCODED_MEMBER_ID,
                storeId,
                request
        );

        // 3. 응답 DTO로 변환 (DTO 내부에 구현된 정적 팩토리 메서드 사용)
        ReviewResponseDTO responseDTO = ReviewResponseDTO.toDTO(newReview);

        // 4. 명시적인 성공 코드와 함께 응답 반환 (HTTP 201 Created)
        return ApiResponse.of(ReviewSuccessCode.REVIEW_CREATE_SUCCESS, responseDTO);
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
    public ApiResponse<ReviewListResponseDTO> getMyReviews(
            Principal principal,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String ratingRange,
            @RequestParam(name = "page") @PageCheck Integer page) {

        long memberId;

        if (principal == null) { //비로그인 상태
            memberId = 1000L;
            System.out.println("경고: 인증 정보(Principal)가 없어 memberId 1000L로 임시 처리합니다.");
        } else {
            //로그인 상태
            memberId = Long.parseLong(principal.getName());
        }

        ReviewListResponseDTO responseDTO = reviewQueryService.getMyReviews(memberId, storeName, ratingRange, page);

        return ApiResponse.onSuccess(responseDTO);
    }
}