package com.dong.couponchecker.domain.service;

import com.dong.couponchecker.domain.Member;
import com.dong.couponchecker.domain.MemberRepository;
import com.dong.couponchecker.util.SHA256;
import com.dong.couponchecker.web.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean postMember(MemberDto memberDto) {
        try {
            Member member = Member.builder()
                    .name(memberDto.getName())
                    .email(memberDto.getEmail())
                    .password(SHA256.encrypt(memberDto.getPassword()))
                    .build();
            memberRepository.save(member);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
