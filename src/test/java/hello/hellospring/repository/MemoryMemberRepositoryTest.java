package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        repository.clear();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("준석팍");
        repository.save(member);
        Member findMember = repository.findById(member.getId()).get();

        System.out.println(member);
        System.out.println(findMember);
        assertThat(member).isEqualTo(findMember);

    }

    @Test
    void findByName() {
        Member member = new Member();
        member.setName("준석팍");
        repository.save(member);
        Member findMember = repository.findByName(member.getName()).get();
        System.out.println(member);
        System.out.println(findMember);
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("준석팍");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("준석팍2");
        repository.save(member2);
        List<Member> list = new ArrayList<>();
        list.add(member1);
        list.add(member2);
        List<Member> allMember = repository.findAll();
        assertThat(allMember.contains(member1)).isTrue();
        assertThat(allMember.contains(member2)).isTrue();
        assertThat(list).isEqualTo(allMember);
        assertThat(allMember.size()).isEqualTo(2);

    }

}