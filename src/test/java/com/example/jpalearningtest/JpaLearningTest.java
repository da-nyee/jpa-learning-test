package com.example.jpalearningtest;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.jpalearningtest.member.Member;
import com.example.jpalearningtest.member.MemberRepository;
import com.example.jpalearningtest.team.Team;
import com.example.jpalearningtest.team.TeamRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class JpaLearningTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName("CascadeType.REMOVE - 부모 엔티티(Team)을 삭제하는 경우")
    @Test
    void cascadeType_Remove_InCaseOfTeamRemoval() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();

        Team team = new Team();

        team.addMember(member1);
        team.addMember(member2);

        teamRepository.save(team);

        // when
        teamRepository.delete(team);

        // then
        List<Team> teams = teamRepository.findAll();
        List<Member> members = memberRepository.findAll();

        assertThat(teams).hasSize(0);
        assertThat(members).hasSize(0);
    }

    @DisplayName("CascadeType.REMOVE - 부모 엔티티(Team)에서 자식 엔티티(Member)를 제거하는 경우")
    @Test
    void cascadeType_Remove_InCaseOfMemberRemovalFromTeam() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();

        Team team = new Team();

        team.addMember(member1);
        team.addMember(member2);

        teamRepository.save(team);

        // when
        team.getMembers().remove(0);

        // then
        List<Team> teams = teamRepository.findAll();
        List<Member> members = memberRepository.findAll();

        assertThat(teams).hasSize(1);
        assertThat(members).hasSize(2);
    }

    @DisplayName("orphanRemoval = true - 부모 엔티티(Team)을 삭제하는 경우")
    @Test
    void orphanRemoval_True_InCaseOfTeamRemoval() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();

        Team team = new Team();

        team.addMember(member1);
        team.addMember(member2);

        teamRepository.save(team);

        // when
        teamRepository.delete(team);

        // then
        List<Team> teams = teamRepository.findAll();
        List<Member> members = memberRepository.findAll();

        assertThat(teams).hasSize(0);
        assertThat(members).hasSize(0);
    }

    @DisplayName("orphanRemoval = true - 부모 엔티티(Team)에서 자식 엔티티(Member)를 제거하는 경우")
    @Test
    void orphanRemoval_True_InCaseOfMemberRemovalFromTeam() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();

        Team team = new Team();

        team.addMember(member1);
        team.addMember(member2);

        teamRepository.save(team);

        // when
        team.getMembers().remove(0);

        // then
        List<Team> teams = teamRepository.findAll();
        List<Member> members = memberRepository.findAll();

        assertThat(teams).hasSize(1);
        assertThat(members).hasSize(1);
    }
}
