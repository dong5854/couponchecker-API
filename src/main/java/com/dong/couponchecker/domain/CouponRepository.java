package com.dong.couponchecker.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<List<Coupon>> findAllByUploader(Member uploader);
    Optional<List<Coupon>> findAllByUploaderId(long id);
    Optional<List<Coupon>> findAllByClub(Club club);
}
