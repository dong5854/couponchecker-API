package com.dong.couponchecker.domain.service;

import com.dong.couponchecker.domain.Member;
import com.dong.couponchecker.domain.MemberRepository;
import com.dong.couponchecker.exception.UnexpectedException;
import com.dong.couponchecker.util.SHA256;
import com.dong.couponchecker.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void postMember(MemberDto memberDto) throws UnexpectedException {
            Member member = Member.builder()
                    .name(memberDto.getName())
                    .email(memberDto.getEmail())
                    .password(SHA256.encrypt(memberDto.getPassword()))
                    .build();
            memberRepository.save(member);
        }
}
