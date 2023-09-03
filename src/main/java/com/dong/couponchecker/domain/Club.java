package com.dong.couponchecker.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.*;
import java.time.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Club {
    @Id @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "club")
    private List<Coupon> coupons = new ArrayList<>();
    @OneToMany(mappedBy = "club")
    private List<UserClub> userClubs = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime createdAt;

    public void addCoupons(Coupon coupon) {
        coupon.setClub(this);
    }
}

