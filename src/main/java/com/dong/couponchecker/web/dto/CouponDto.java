package com.dong.couponchecker.web.dto;

import lombok.*;

@ToString
@Getter
@RequiredArgsConstructor
public class CouponCreateDto {
    private final String name;
    private final String url;
    private final String uploaderId;
    private final String groupId;
}
