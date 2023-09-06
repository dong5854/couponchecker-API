package com.dong.couponchecker.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    void deleteMemberByEmail(String email);
}
