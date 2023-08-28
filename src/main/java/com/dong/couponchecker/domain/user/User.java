package com.dong.couponchecker.domain.user;

import com.dong.couponchecker.domain.coupon.Coupon;
import com.dong.couponchecker.domain.club.Club;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.*;
import java.time.*;

@NoArgsConstructor @AllArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "uploader")
    private List<Coupon> uploadedCoupons = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Coupon> usedCoupons = new ArrayList<>();
    @ManyToMany
    private List<Club> clubs = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime createdAt;
}
