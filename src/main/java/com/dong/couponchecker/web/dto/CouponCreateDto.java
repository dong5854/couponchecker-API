package com.dong.couponchecker.web.dto;

import lombok.*;

@ToString
@Builder @Getter
public class CouponCreateDto {
    String name;
    String url;
    String uploaderId;
    String groupId;
}
