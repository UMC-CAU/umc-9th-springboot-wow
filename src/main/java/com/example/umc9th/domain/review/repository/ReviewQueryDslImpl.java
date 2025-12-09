package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    //검색 API
    @Override
    public List<Review> searchReview(
        Predicate predicate
    ){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        //Q클래스 선언
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .fetch();

    }

    @Override
    public Page<Review> findMyReviews(Long memberId, String storeName, String ratingRange, Pageable pageable) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 리뷰의 작성자 ID가 현재 로그인한 memberId와 일치해야 함
        builder.and(review.member.id.eq(memberId));

        if (storeName != null && !storeName.trim().isEmpty()) {
            // 가게 이름이 포함되는 방식(평범한 검색 방식)으로 했는데 이것보다는 storeId로 하는 게 확실한지 고민해보기
            builder.and(review.store.name.contains(storeName));
        }

        if (ratingRange != null && !ratingRange.trim().isEmpty()) {
            addRatingRangeCondition(builder, review, ratingRange.trim());
        }

        List<Review> content = queryFactory
                .selectFrom(review)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 개수 세는 쿼리
        Long totalCount = queryFactory
                .select(review.count())
                .from(review)
                .where(builder)
                .fetchOne();

        // Page 객체로 반환
        return new PageImpl<>(content, pageable, totalCount);
    }

    private void addRatingRangeCondition(BooleanBuilder builder, QReview review, String ratingRange) {
        try {
            int rating = Integer.parseInt(ratingRange);

            if (rating == 5) {
                builder.and(review.rating.eq(BigDecimal.valueOf(5.0f)));
            } else if (rating >= 1 && rating <= 4) {
                float lowerBound = (float) rating;
                float upperBound = (float) (rating + 1);

                builder.and(review.rating.goe(lowerBound).and(review.rating.lt(upperBound)));
            }
        } catch (NumberFormatException e) {
            //혹시 모르니까 입력된 ratingRange가 숫자가 아닐 경우 조건 추가를 생략
        }
    }
}
