package com.example.jpalearningtest.member.application.dto;

public class MemberRequestDto {

    private String name;

    private MemberRequestDto() {
    }

    public MemberRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
