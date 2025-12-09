package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.entity.Review;

public interface ReviewCommandService {

    Review addReview(Long memberId, Long storeId, ReviewRequestDTO request);
}