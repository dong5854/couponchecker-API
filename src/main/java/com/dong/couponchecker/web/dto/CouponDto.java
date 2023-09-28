package com.dong.couponchecker.web.dto;

import lombok.*;

@ToString
@Getter
@RequiredArgsConstructor
public class CouponDto {
    private final String name;
    private final String url;
    private final long uploaderId;
    private final long clubId;
}
