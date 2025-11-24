package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.example.umc9th.global.apiPayload.exception.ExceptionCode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review addReview(Long memberId, Long storeId, ReviewRequestDTO request) {

        // 1. 회원 및 가게 엔티티 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ExceptionCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ExceptionCode.STORE_NOT_FOUND)); // STORE_NOT_FOUND는 ExceptionCode에 정의되어 있다고 가정

        // 2. Review 엔티티 생성 및 저장
        Review newReview = Review.builder()
                .rating(request.getRating())
                .content(request.getContent())
                .member(member)
                .store(store)
                .build();

        return reviewRepository.save(newReview);
    }
}