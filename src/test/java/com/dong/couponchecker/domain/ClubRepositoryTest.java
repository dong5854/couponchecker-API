package com.dong.couponchecker.domain;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@Transactional
public class ClubRepositoryTest {

    @Autowired
    private ClubRepository clubRepository;

    @Test
    public void testClubInsert() {
        // 테스트 코드 작성하기
    }

    @Test
    public void testClubRead() {
        // 테스트 코드 작성하기
    }

    @Test
    public void testClubUpdate() {
        // 테스트 코드 작성하기
    }

    @Test
    public void testClubDelete() {
        // 테스트 코드 작성하기
    }
}
