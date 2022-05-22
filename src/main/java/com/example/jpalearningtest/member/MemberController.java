package com.example.jpalearningtest.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/api/members")
    public ResponseEntity<Void> save(String name) throws Exception {
        memberService.save(name);
        return ResponseEntity.ok().build();
    }
}
