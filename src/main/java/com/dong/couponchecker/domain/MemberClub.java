package com.dong.couponchecker.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
public class MemberClub {

    @Id @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Club club;

    @CreationTimestamp
    private LocalDateTime joinedAt;

    public MemberClub(Member member, Club club) {
        member.addMemberClubs(this);
        club.addMemberClub(this);
        this.member = member;
        this.club = club;
    }
}
