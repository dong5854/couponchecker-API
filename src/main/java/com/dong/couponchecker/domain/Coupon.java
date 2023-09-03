package com.dong.couponchecker.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.*;

@Getter @Setter
@NoArgsConstructor
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

    public void setUploader(User user) {
        if(this.user != null) {
            this.user.getUploadedCoupons().remove(this);
        }
        this.user = user;
        user.getUploadedCoupons().add(this);
    }

    public void setClub(Club club) {
        if(this.club != null) {
            this.club.getCoupons().remove(this);
        }
        this.club = club;
        club.getCoupons().add(this);
    }

    public void setUser(User user) {
        if(this.user != null) {
            this.user.getUsedCoupons().remove(this);
        }
        this.user = user;
        user.getUsedCoupons().add(this);
    }

    @Builder
    public Coupon(String name, String url, LocalDateTime createdAt, LocalDateTime usedAt, User uploader, Club club, User user) {
        this.name = name;
        this.url = url;
        this.createdAt = createdAt;
        this.usedAt = usedAt;
        this.uploader = uploader;
        this.club = club;
        this.user = user;
    }
}
