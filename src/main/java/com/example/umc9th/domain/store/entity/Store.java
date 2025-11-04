package com.example.umc9th.domain.store.entity;

import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.food.entity.Food;
import com.example.umc9th.domain.store.enums.Address;
import com.example.umc9th.domain.store.enums.BusinessStatus;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store")
public class Store extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //양방향 매핑 필요x
    @JoinColumn(name = "food_id")
    private Food food;

    //기존에는 location 엔티티를 따로 만들었는데 관계 설정이 복잡해 그냥 Address ENUM으로..
    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "business_status", length = 15)
    private BusinessStatus businessStatus;

}
