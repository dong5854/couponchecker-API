package com.dong.couponchecker.web;

import java.util.*;
import java.util.stream.Collectors;

import com.dong.couponchecker.domain.Coupon;
import com.dong.couponchecker.domain.service.CouponService;
import com.dong.couponchecker.exception.DataNotFoundException;
import com.dong.couponchecker.web.dto.CouponDto;
import com.dong.couponchecker.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/coupon")
    public ResponseEntity<List<CouponDto>> findAllByClubId(@RequestParam long clubId) throws DataNotFoundException {
        List<Coupon> coupons = couponService.findAllByClubId(clubId);
        return new ResponseEntity<>(coupons.stream().map(Coupon::toDto).collect(Collectors.toList()), HttpStatus.OK);
    }
}
