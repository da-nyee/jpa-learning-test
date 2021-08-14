package com.example.jpalearningtest.team;

import com.example.jpalearningtest.member.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();

//    @OneToMany(
//        mappedBy = "team",
//        fetch = FetchType.LAZY,
//        cascade = CascadeType.ALL
//    )
//    private List<Member> members = new ArrayList<>();

//    @OneToMany(
//        mappedBy = "team",
//        fetch = FetchType.LAZY,
//        cascade = CascadeType.PERSIST,
//        orphanRemoval = true
//    )
//    private List<Member> members = new ArrayList<>();

    public Team() {
    }

    // 연관 관계 편의 메소드
    public void addMember(Member member) {
        members.add(member);
        member.setTeam(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
