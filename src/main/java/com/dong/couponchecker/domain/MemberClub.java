package com.dong.couponchecker.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class MemberClub {

    @Id @GeneratedValue
    private long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Club club;

    @CreationTimestamp
    private LocalDateTime joinedAt;
}
