package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.umc9th.domain.review.dto.ReviewListResponseDTO;
import com.example.umc9th.global.validation.annotation.PageCheck;

import java.security.Principal;
import java.util.List;

@Tag(name = "Review", description = "리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 새로운 리뷰 추가 API
    @Operation(summary = "새로운 리뷰 추가", description = "특정 가게에 사용자가 새로운 리뷰를 작성합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "리뷰 작성 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "요청 본문(DTO) 유효성 검사 실패")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게 또는 멤버를 찾을 수 없음")
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO> addReview(
            @Parameter(description = "리뷰를 작성할 가게의 ID", required = true)
            @PathVariable(name = "storeId") Long storeId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "리뷰 생성에 필요한 정보", required = true)
            @RequestBody @Valid ReviewRequestDTO request) {

        final Long HARDCODED_MEMBER_ID = 1000L;

        Review newReview = reviewCommandService.addReview(
                HARDCODED_MEMBER_ID,
                storeId,
                request
        );

        ReviewResponseDTO responseDTO = ReviewResponseDTO.toDTO(newReview);

        return ApiResponse.of(ReviewSuccessCode.REVIEW_CREATE_SUCCESS, responseDTO);
    }

    @Operation(summary = "리뷰 검색", description = "특정 조건(제목, 내용 등)으로 리뷰를 검색합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "검색 성공")
    @GetMapping("/reviews/search")
    public ApiResponse<List<ReviewResponseDTO>> searchReview(
            @Parameter(description = "검색 키워드", required = true)
            @RequestParam String query,
            @Parameter(description = "검색 유형 (예: 'title', 'content', 'storeName')", required = true)
            @RequestParam String type
    ) {
        List<ReviewResponseDTO> responseDTOs = reviewQueryService.searchReview(type, query);

        return ApiResponse.onSuccess(responseDTOs);
    }

    @Operation(summary = "내 리뷰 목록 조회", description = "로그인된 사용자가 작성한 리뷰 목록을 필터링 및 페이징하여 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "리뷰 목록 조회 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "페이지 번호 유효성 오류 (1 미만)")
    @GetMapping("/reviews/me")
    public ApiResponse<ReviewListResponseDTO> getMyReviews(
            Principal principal,
            @Parameter(description = "가게 이름으로 필터링 (선택적)", required = false)
            @RequestParam(required = false) String storeName,
            @Parameter(description = "평점 범위 필터링 (선택적, 예: '3-5')", required = false)
            @RequestParam(required = false) String ratingRange,
            @Parameter(description = "조회할 페이지 번호 (1부터 시작, 10개 단위)", required = true)
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