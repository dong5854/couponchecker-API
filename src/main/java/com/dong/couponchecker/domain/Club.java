package com.dong.couponchecker.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;
import java.time.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Club {
    @Id @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    private List<Coupon> coupons = new ArrayList<>();
    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MemberClub> memberClubs = new ArrayList<>();
    @CreatedDate
    private LocalDateTime createdAt;

    public void addCoupons(Coupon coupon) {
        coupon.setClub(this);
    }
    public void addMember(Member member) {
        new MemberClub(member, this);
    }

    public Club(String name, String password) {
        this.name = name;
        this.password = password;
    }
}