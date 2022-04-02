package com.example.jpalearningtest.member.application.dto;

public class MemberResponseDto {

    private Long id;
    private String name;

    private MemberResponseDto() {
    }

    public MemberResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
