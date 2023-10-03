package com.dong.couponchecker.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;


@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class MemberClubId implements Serializable {
    private long memberId;
    private long clubId;

    public MemberClubId(long memberId, long clubId) {
        this.memberId = memberId;
        this.clubId = clubId;
    }
}
