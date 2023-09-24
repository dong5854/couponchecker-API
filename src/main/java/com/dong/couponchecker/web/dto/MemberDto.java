package com.dong.couponchecker.web.dto;

import lombok.*;

@Getter
public class MemberDto {
    private String email;
    private String name;
    private String password;

    @Builder
    public MemberDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
