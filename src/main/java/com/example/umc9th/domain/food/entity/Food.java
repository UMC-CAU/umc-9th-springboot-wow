// food가 단순한 음식명으로 사용하는 게 아니라,
// 사용자 취향이랑 가게에 반영하는 음식 카테고리로 사용해서
// 따로 빼는 게 나을 것 같아서 빼놨는데 이게 나을까.. 아니면 그냥 워크북처럼 member 도메인의 하위로 두는 게 나을까........

package com.example.umc9th.domain.food.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.food.enums.FoodName;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "food")
public class Food extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private FoodName name;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFood> membersWhoPrefer = new ArrayList<>();

}
