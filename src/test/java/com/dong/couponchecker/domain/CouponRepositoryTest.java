package com.dong.couponchecker.domain;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class CouponRepositoryTest {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private MemberRepository memberRepository;

    private Member testUploader;
    private Member testMemberWhoUsed;
    private Club testClub;
    @BeforeEach
    public void setUp() {
        testUploader = Member.builder().
                email("test@email.com").
                name("김테스트").
                password("test1234!@#").
                build();
        memberRepository.save(testUploader);

        testMemberWhoUsed = Member.builder().
                email("testWhoUsee@email.com").
                name("이사용").
                password("use123!@#").
                build();
        memberRepository.save(testMemberWhoUsed);

        testClub = new Club("테스트용 클럽", "testClub123!@#");
        clubRepository.save(testClub);
    }

    @Test
    public void testCouponInsertRead() {
        // given
        Coupon testCoupon = new Coupon("테스트 쿠폰", "dummyUrl", testUploader, testClub);

        // when
        Coupon savedCoupon = couponRepository.save(testCoupon);
        long id = savedCoupon.getId();

        // then
        Optional<Coupon> foundCoupon = couponRepository.findById(id);
        assertThat(foundCoupon.isPresent()).isTrue();
        assertThat(foundCoupon.get().getName()).isEqualTo(testCoupon.getName());
        assertThat(foundCoupon.get().getUrl()).isEqualTo(testCoupon.getUrl());
        assertThat(foundCoupon.get().getUploader()).isEqualTo(testCoupon.getUploader());
        assertThat(foundCoupon.get().getMemberWhoUsed()).isNull();
        assertThat(foundCoupon.get().getUsedAt()).isNull();
        assertThat(foundCoupon.get().getCreatedAt()).isNotNull();
    }

    @Test
    public void testCouponSetMemberWhoUsed() {
        // given
        Coupon testCoupon = new Coupon("테스트 쿠폰", "dummyUrl", testUploader, testClub);

        // when
        Coupon savedCoupon = couponRepository.save(testCoupon);
        LocalDateTime createdAt = savedCoupon.getCreatedAt();
        savedCoupon.setMemberWhoUsed(testMemberWhoUsed);

        // then
        assertThat(savedCoupon.getMemberWhoUsed()).isEqualTo(testMemberWhoUsed);
        assertThat(savedCoupon.getCreatedAt()).isEqualTo(createdAt);
        assertThat(savedCoupon.getUsedAt()).isNotNull();
        System.out.println("생성일: " + savedCoupon.getCreatedAt());
        System.out.println("사용일: " + savedCoupon.getUsedAt());
    }

    @Test
    public void testCouponUpdate() {
        // given
        Coupon testCoupon = new Coupon("테스트 쿠폰", "dummyUrl", testUploader, testClub);

        // when
        Coupon savedCoupon = couponRepository.save(testCoupon);
        long id = savedCoupon.getId();

        // then
        Optional<Coupon> foundCoupon = couponRepository.findById(id);
        assertThat(foundCoupon.isPresent()).isTrue();
        assertThat(foundCoupon.get().getName()).isEqualTo(testCoupon.getName());
        assertThat(foundCoupon.get().getUrl()).isEqualTo(testCoupon.getUrl());
        assertThat(foundCoupon.get().getUploader()).isEqualTo(testCoupon.getUploader());
    }

    @Test
    public void testCouponDelete() {
        // given
        Coupon testCoupon = new Coupon("테스트 쿠폰", "dummyUrl", testUploader, testClub);

        Coupon savedCoupon = couponRepository.save(testCoupon);

        // when
        couponRepository.deleteById(savedCoupon.getId());

        // then
        Optional<Coupon> removedCoupon = couponRepository.findById(savedCoupon.getId());
        assertThat(removedCoupon.isPresent()).isFalse();
    }
}
