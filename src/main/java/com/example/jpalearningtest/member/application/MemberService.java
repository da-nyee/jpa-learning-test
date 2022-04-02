package com.example.jpalearningtest.member.application;

import static java.util.stream.Collectors.toList;

import com.example.jpalearningtest.member.application.dto.MemberResponseDto;
import com.example.jpalearningtest.member.domain.Member;
import com.example.jpalearningtest.member.domain.MemberRepository;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final EntityManager entityManager;

    public MemberService(
        MemberRepository memberRepository,
        EntityManager entityManager
    ) {
        this.memberRepository = memberRepository;
        this.entityManager = entityManager;
    }

    public List<MemberResponseDto> readAll() {
        List<Member> members = memberRepository.findAll();

        return members.stream()
            .map(member -> new MemberResponseDto(member.getId(), member.getName()))
            .collect(toList());
    }

    public void update(String name) {
        List<Member> members = memberRepository.findAll();

        Member firstMember = members.get(0);
        firstMember.setName(name);

        entityManager.flush();
    }
}
