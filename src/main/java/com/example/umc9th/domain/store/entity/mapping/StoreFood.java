package com.example.umc9th.domain.store.entity.mapping;

import com.example.umc9th.domain.food.entity.Food;
import com.example.umc9th.domain.store.entity.Store;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store_food") // 실제 DB 테이블 이름 지정
public class StoreFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 💡 Store와의 N:1 관계 설정 (연관 관계의 주인)
    // 외래 키: store_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // 💡 Food와의 N:1 관계 설정 (연관 관계의 주인)
    // 외래 키: food_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

}