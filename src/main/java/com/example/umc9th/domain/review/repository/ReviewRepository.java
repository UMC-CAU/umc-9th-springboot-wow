package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    // save(Review review) 메서드는 JpaRepository에 이미 정의되어 있음.
    List<Review> findAllByMemberId(Long memberId);
}
