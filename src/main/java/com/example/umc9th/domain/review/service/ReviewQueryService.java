package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    public List<Review> searchReview(String type, String query){
        //Q클래스 정의
        QReview review = QReview.review;
        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();
        //BooleanBuilder 사용

        // 동적 쿼리: 검색 조건
        // 1. 지역(location) 검색 조건
        if (type.equals("location")) {
            builder.and(review.store.location.name.contains(query));
        }

        // 2. 별점(star) 검색 조건 (>= Greater Than or Equal)
        else if (type.equals("rating")) {
            builder.and(review.rating.goe(Float.parseFloat(query)));
        }

        // 3. 지역(location) 및 별점(star) 복합 검색 조건
        else if (type.equals("both")) {
            String[] parts = query.split("&");
            if (parts.length == 2) {
                String firstQuery = parts[0];
                String secondQuery = parts[1];

                builder.and(review.store.location.name.contains(firstQuery));

                builder.and(review.rating.goe(Float.parseFloat(secondQuery)));
            }
        }
        //Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);
        //리턴
        return reviewList;
    }
    public List<Review> getMyReviews(Long memberId, String storeName, String ratingRange) {
        // 1. 필수 조건 (memberId)과 선택적 필터 (storeName, ratingRange)를
        //    모두 QueryDsl 커스텀 Repository 메서드로 전달합니다.

        // 2. Repository 계층에서 memberId를 기본 조건으로 BooleanBuilder에 조건을 조합하여 쿼리를 실행합니다.        return reviewRepository.findMyReviews(memberId, storeName, ratingRange);
        return reviewRepository.findMyReviews(memberId, storeName, ratingRange);
    }
}
