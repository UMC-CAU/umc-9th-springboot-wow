package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.mission.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", length = 15)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ONGOING;

    @Column(name = "min_order")
    private Integer minimumOrderAmount;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "point")
    private Integer point;

}