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

    @OneToMany(mappedBy = "uploader", fetch = FetchType.LAZY)
    private List<Coupon> uploadedCoupons = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Coupon> usedCoupons = new ArrayList<>();
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberClub> memberClubs = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime createdAt;

    public void addUploadedCoupons(Coupon coupon) {
        coupon.setUploader(this);
    }

    public void addUsedCoupons(Coupon coupon) {
        coupon.setUser(this);
    }
    public void addMemberClubs(MemberClub memberClub) { memberClubs.add(memberClub);}

    @Builder
    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
