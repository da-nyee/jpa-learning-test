package com.example.jpalearningtest.member;

import ch.qos.logback.core.joran.spi.ActionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
@Transactional(rollbackFor = {Exception.class})
public class MemberService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void save(String name) throws Exception {
        Member member = new Member(name);
        memberRepository.save(member);

        if ("커밋".equals(name)) {
            LOG.info("[Exception] member 저장 성공 - 커밋 O, 롤백 X");
            throw new ActionException();
        }
        if ("롤백 1".equals(name)) {
            LOG.info("[RuntimeException] member 저장 실패 - 커밋 X, 롤백 O");
            throw new IllegalStateException();
        }
        if ("롤백 2".equals(name)) {
            LOG.info("[Error] member 저장 실패 - 커밋 X, 롤백 O");
            throw new IllegalAccessError();
        }
        LOG.info("member 저장 성공 - 커밋 O, 롤백 X");
    }
}
