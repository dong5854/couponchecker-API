package com.dong.couponchecker.domain.club;

import com.dong.couponchecker.domain.coupon.Coupon;
import com.dong.couponchecker.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.*;
import java.time.*;

@NoArgsConstructor @AllArgsConstructor
@Entity
public class Club {
    @Id @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @OneToMany
    private List<Coupon> coupons = new ArrayList<>();
    @ManyToMany
    private List<User> users = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime createdAt;
}

