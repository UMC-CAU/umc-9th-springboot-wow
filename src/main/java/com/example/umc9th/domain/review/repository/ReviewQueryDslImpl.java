package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository; // 가정
import com.example.umc9th.domain.review.repository.ReviewQueryDsl; // 가정
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.example.umc9th.domain.review.entity.QReview; // Q클래스 import 가정

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service; // @Service 어노테이션 사용

import java.util.List;

@Service // 신규 *
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final ReviewRepository reviewRepository;
    private final EntityManager em;

    // 검색 API
    @Override
    public List<Review> searchReview(
            Predicate predicate
    ) {
        // JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // Q클래스 선언
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .fetch();
    }
}
