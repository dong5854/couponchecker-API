package com.dong.couponchecker.domain;

import com.dong.couponchecker.web.dto.CouponDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.*;

@Getter @Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Coupon {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime usedAt;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Member uploader;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member memberWhoUsed;

    public void setUploader(Member uploader) {
        if(this.uploader != null) {
            this.uploader.getUploadedCoupons().remove(this);
        }
        this.uploader = uploader;
        uploader.getUploadedCoupons().add(this);
    }

    public void setClub(Club club) {
        if(this.club != null) {
            this.club.getCoupons().remove(this);
        }
        this.club = club;
        club.getCoupons().add(this);
    }

    public void setMemberWhoUsed(Member memberWhoUsed) {
        if(this.memberWhoUsed != null) {
            this.memberWhoUsed.getUsedCoupons().remove(this);
        }
        this.memberWhoUsed = memberWhoUsed;
        this.usedAt =  LocalDateTime.now();
        memberWhoUsed.getUsedCoupons().add(this);
    }

    public CouponDto toDto() {
        return new CouponDto(this.name, this.url, this.uploader.getId(), this.club.getId());
    }

    @Builder
    public Coupon(String name, String url, Member uploader, Club club) {
        this.name = name;
        this.url = url;
        this.setUploader(uploader);
        this.setClub(club);
    }
}
