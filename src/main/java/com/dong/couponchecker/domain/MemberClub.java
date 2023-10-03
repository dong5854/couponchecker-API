package com.dong.couponchecker.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
public class MemberClub {

    @EmbeddedId
    private MemberClubId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Member member;

    @MapsId("clubId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Club club;

    @CreationTimestamp
    private LocalDateTime joinedAt;

    public MemberClub(Member member, Club club) {
        member.getMemberClubs().add(this);
        club.getMemberClubs().add(this);
        this.member = member;
        this.club = club;
    }

    public void remove() {
        member.getMemberClubs().remove(this);
        club.getMemberClubs().remove(this);
    }
}
