package com.dong.couponchecker.domain.service;

import com.dong.couponchecker.domain.*;
import com.dong.couponchecker.exception.DataNotFoundException;
import com.dong.couponchecker.exception.UnexpectedException;
import com.dong.couponchecker.util.SHA256;
import com.dong.couponchecker.web.dto.CouponDto;
import com.dong.couponchecker.web.dto.MemberDto;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;

    void postCoupon(CouponDto couponDto) throws DataNotFoundException {
        // TODO: S3 버킷에 업로드 후 해당 url을 넣도록 변경, as-is: "dummyUrl"
        Optional<Member> uploader = memberRepository.findById(couponDto.getUploaderId());
        if (uploader.isEmpty()) throw new DataNotFoundException();
        Optional<Club> club = clubRepository.findById(couponDto.getClubId());
        if (club.isEmpty()) throw new DataNotFoundException();
        Coupon coupon = Coupon.builder().
                name(couponDto.getName()).
                url("dummyUrl").
                uploader(uploader.get()).
                club(club.get()).build();
        couponRepository.save(coupon);
    }
    List<Coupon> findAllByUploader(MemberDto memberDto) throws DataNotFoundException, UnexpectedException {
        Member uploader = Member.builder()
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .password(SHA256.encrypt(memberDto.getPassword()))
                .build();
        Optional<List<Coupon>> coupons = couponRepository.findAllByUploader(uploader);
        if (coupons.isEmpty()) throw new DataNotFoundException();
        return coupons.get();
    }
    List<Coupon> findAllByClub(long clubId) throws DataNotFoundException {
        Optional<Club> club = clubRepository.findById(clubId);
        if (club.isEmpty()) throw new DataNotFoundException();
        Optional<List<Coupon>> coupons = couponRepository.findAllByClub(club.get());
        if (coupons.isEmpty()) throw new DataNotFoundException();
        return coupons.get();
    }
}
