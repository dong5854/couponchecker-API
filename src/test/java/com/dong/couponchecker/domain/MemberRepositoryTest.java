package com.dong.couponchecker.domain;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository userRepository;

    @Test
    public void testMemberInsertRead() {
        // given
        Member testUser = Member.builder().
                        email("test@email.com").
                        name("김테스트").
                        password("test1234!@#").
                        build();

        // when
        Member savedUser = userRepository.save(testUser);
        long id = savedUser.getId();

        // then
        Optional<Member> foundUser = userRepository.findById(id);
        assertThat(foundUser.isPresent()).isTrue();
        assertThat(foundUser.get().getEmail()).isEqualTo(testUser.getEmail());
        assertThat(foundUser.get().getName()).isEqualTo(testUser.getName());
        assertThat(foundUser.get().getPassword()).isEqualTo(testUser.getPassword());
    }

    @Test
    public void testMemberUpdate() {
        // Test updating an existing entity
    }

    @Test
    public void testMemberDelete() {
        // Test deleting an existing entity
    }
}
