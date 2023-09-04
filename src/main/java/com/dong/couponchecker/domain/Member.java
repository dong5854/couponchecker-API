package com.dong.couponchecker.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.*;
import java.time.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Member {
    @Id @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "uploader")
    private List<Coupon> uploadedCoupons = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Coupon> usedCoupons = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<MemberClub> userClubs = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime createdAt;

    public void addUploadedCoupons(Coupon coupon) {
        coupon.setUploader(this);
    }

    public void addUsedCoupons(Coupon coupon) {
        coupon.setUser(this);
    }

    @Builder
    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
