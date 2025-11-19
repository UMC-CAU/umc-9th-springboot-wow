package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    public List<ReviewResponseDTO> searchReview(String type, String query){
        //Q클래스 정의
        QReview review = QReview.review;
        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용
        // 1. 지역(location) 검색 조건
        if (type.equals("location")) {
            builder.and(review.store.location.name.contains(query));
        }

        // 2. 별점(rating) 검색 조건
        else if (type.equals("rating")) {
            builder.and(review.rating.goe(BigDecimal.valueOf(Float.parseFloat(query))));        }

        // 3. 지역(location) 및 별점(rating) 복합 검색 조건
        else if (type.equals("both")) {
            String[] parts = query.split("&");
            if (parts.length == 2) {
                String firstQuery = parts[0];
                String secondQuery = parts[1];

                builder.and(review.store.location.name.contains(firstQuery));

                builder.and(review.rating.goe(BigDecimal.valueOf(Float.parseFloat(secondQuery))));            }
        }
        //Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);
        // 서비스 계층이 엔티티를 ＤＴＯ로 변환하여 반환하도록
        return reviewList.stream()
                .map(ReviewResponseDTO::toDTO)
                .collect(Collectors.toList());
    }
    public List<ReviewResponseDTO> getMyReviews(Long memberId, String storeName, String ratingRange) {
        // 1. 필수 조건 (memberId)과 선택적 필터 (storeName, ratingRange)를
        //    모두 QueryDsl 커스텀 Repository 메서드로 전달
        List<Review> reviews = reviewRepository.findMyReviews(memberId, storeName, ratingRange);

        return reviews.stream()
                .map(ReviewResponseDTO::toDTO)
                .collect(Collectors.toList());
    }
}
