package com.dong.couponchecker.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class UserClub {

    @Id @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Club club;

    @CreationTimestamp
    private LocalDateTime joinedAt;
}
