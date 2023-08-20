package com.dong.couponchecker.domain.coupon;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.*;


@NoArgsConstructor @AllArgsConstructor
@Entity
public class CouponEntity {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime usedAt;
    @Column(nullable = false)
    private Long uploaderId;
    @Column(nullable = false)
    private Long groupId;
    // 일반적인 유저(user)가 아닌 쿠폰을 사용한 사람(user)의 id
    private Long userId;
}
