package com.dong.couponchecker.web;

import com.dong.couponchecker.domain.service.MemberService;
import com.dong.couponchecker.exception.UnexpectedException;
import com.dong.couponchecker.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<String> postMember(@RequestBody MemberDto memberDto) throws UnexpectedException {
        memberService.postMember(memberDto);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
