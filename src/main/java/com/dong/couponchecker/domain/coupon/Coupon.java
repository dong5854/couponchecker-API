package com.dong.couponchecker.domain.coupon;

import com.dong.couponchecker.domain.club.Club;
import com.dong.couponchecker.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.*;

@NoArgsConstructor @AllArgsConstructor
@Entity
public class Coupon {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime usedAt;
    @ManyToOne
    private User uploader;
    @ManyToOne
    private Club club;
    // 일반적인 '유저'(user)가 아닌 '쿠폰을 사용한 사람'(user)라는 의미
    @ManyToOne
    private User user;
}
