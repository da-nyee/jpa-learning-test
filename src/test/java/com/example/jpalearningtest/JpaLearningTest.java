package com.example.jpalearningtest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.tuple;

import com.example.jpalearningtest.member.application.MemberService;
import com.example.jpalearningtest.member.application.dto.MemberResponseDto;
import com.example.jpalearningtest.member.domain.Member;
import com.example.jpalearningtest.member.domain.MemberRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaLearningTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member dani = new Member("dani");
        Member daisy = new Member("daisy");
        List<Member> members = List.of(dani, daisy);

        memberRepository.saveAll(members);
    }

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
    }

    @DisplayName("멤버 전체를 조회한다.")
    @Test
    void readAll() {
        // when
        List<MemberResponseDto> responses = memberService.readAll();

        // then
        assertThat(responses).hasSize(2);
        assertThat(responses)
            .extracting("name")
            .containsExactlyInAnyOrder("dani", "daisy");
    }

    @DisplayName("멤버를 수정한다.")
    @Test
    void update() {
        // when
        // then
        assertThatCode(() -> memberService.update("dani"))
            .doesNotThrowAnyException();
    }
}
