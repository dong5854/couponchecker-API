package com.dong.couponchecker.domain;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

@DataJpaTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testMemberInsertRead() {
        // given
        Member testUser = Member.builder().
                        email("test@email.com").
                        name("김테스트").
                        password("test1234!@#").
                        build();

        // when
        Member savedUser = memberRepository.save(testUser);
        long id = savedUser.getId();

        // then
        Optional<Member> foundUser = memberRepository.findById(id);
        assertThat(foundUser.isPresent()).isTrue();
        assertThat(foundUser.get().getEmail()).isEqualTo(testUser.getEmail());
        assertThat(foundUser.get().getName()).isEqualTo(testUser.getName());
        assertThat(foundUser.get().getPassword()).isEqualTo(testUser.getPassword());
    }

    @Test
    public void testMemberUpdate() {
        // given
        Member testUser = Member.builder().
                email("test@email.com").
                name("김테스트").
                password("test1234!@#").
                build();

        memberRepository.save(testUser);

        // when
        Optional<Member> savedMember = memberRepository.findByEmail("test@email.com");
        savedMember.get().setName("김업데이트");
        memberRepository.save(savedMember.get());

        // then
        Optional<Member> updatedMember = memberRepository.findByEmail("test@email.com");
        assertThat(updatedMember.isPresent()).isTrue();
        assertThat(updatedMember.get().getName()).isEqualTo("김업데이트");
    }

    @Test
    public void testMemberDelete() {
        // given
        Member testUser = Member.builder().
                email("test@email.com").
                name("김테스트").
                password("test1234!@#").
                build();

        memberRepository.save(testUser);

        // when
        memberRepository.deleteMemberByEmail("test@email.com");

        // then
        Optional<Member> removedMember = memberRepository.findByEmail("test@email.com");
        assertThat(removedMember.isPresent()).isFalse();
    }
}
