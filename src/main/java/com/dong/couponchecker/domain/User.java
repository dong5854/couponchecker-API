package com.dong.couponchecker.domain;

import com.dong.couponchecker.domain.Coupon;
import com.dong.couponchecker.domain.Club;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.*;
import java.time.*;

@Getter @Setter
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

    public void addUploadedCoupons(Coupon coupon) {
        coupon.setUploader(this);
    }

    public void addUsedCoupons(Coupon coupon) {
        coupon.setUser(this);
    }
}
