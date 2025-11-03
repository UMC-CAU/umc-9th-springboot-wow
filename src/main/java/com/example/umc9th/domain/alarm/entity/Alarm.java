package com.example.umc9th.domain.alarm.entity;

import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.alarm.enums.AlarmType;
import com.example.umc9th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm")

public class Alarm extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_confirmed", nullable = false)
    @Builder.Default
    private Boolean is_confirmed = false;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "body", length = 500, nullable = false)
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(name = "alarm_type", nullable = false)
    private AlarmType alarmType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 이 필드는 notice, review_reply, inquiry_response의 ID 중 하나를 저장하므로
    @Column(name = "target_id", nullable = false)
    private Long targetId;

}
