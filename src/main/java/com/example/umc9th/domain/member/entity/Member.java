// member-review 관계는 1:N 관계, 사용자가 자신의 리뷰를 모두 조회할 수 있도록 하기 위해 양방향 매핑함.
// 사용자의 위치와 가게의 위치가 동 단위까지는 같아야 함. (Member-Store)
// + 사용자의 선호 음식이 가게의 음식과 같아야 함. (MemberFood-Store)
// => 사용자에게 미션 배정됨. 이걸 어케 작성해야되지..

package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.location.entity.Location;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.MemberStatus;
import com.example.umc9th.global.auth.enums.SNSType;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "phone_num", length = 15)
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MemberStatus status = MemberStatus.ACTIVE;

    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;

    @Column(name = "mission_num", nullable = false)
    private Integer missionNum;

    @Column(name = "total_point", nullable = false)
    private Integer totalPoint;

    @Column(name = "sns_type", length = 20)
    @Enumerated(EnumType.STRING)
    private SNSType sns_type;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFood> preferredFoods = new ArrayList<>();

    //관계의 주인이 아님. 오직 읽기만 가능한 쪽.
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissionList = new ArrayList<>();

}