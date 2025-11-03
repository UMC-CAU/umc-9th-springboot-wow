// member-review 관계는 1:N 관계, 사용자가 자신의 리뷰를 모두 조회할 수 있도록 하기 위해 양방향 매핑함.
// review-reply 관계는 cascade 설정을 가능하게 하기 위해 양방향 매핑함.

package com.example.umc9th.domain.review.entity;

import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review")
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "content")
    private String content;

    //관계의 owner. 객체의 상태를 변경하면 db의 FK 컬럼에 영향을 줌.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //owner
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
    private ReviewReply reply;
}
