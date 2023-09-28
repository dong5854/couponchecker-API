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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Member uploader;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Club club;
    // 일반적인 '유저'(user)가 아닌 '쿠폰을 사용한 사람'(user)라는 의미
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Member member;

    public void setUploader(Member member) {
        if(this.member != null) {
            this.member.getUploadedCoupons().remove(this);
        }
        this.member = member;
        member.getUploadedCoupons().add(this);
    }

    public void setClub(Club club) {
        if(this.club != null) {
            this.club.getCoupons().remove(this);
        }
        this.club = club;
        club.getCoupons().add(this);
    }

    public void setUser(Member member) {
        if(this.member != null) {
            this.member.getUsedCoupons().remove(this);
        }
        this.member = member;
        member.getUsedCoupons().add(this);
    }

    @Builder
    public Coupon(String name, String url, Member uploader, Club club) {
        this.name = name;
        this.url = url;
        this.setUploader(uploader);
        this.setClub(club);
    }
}
