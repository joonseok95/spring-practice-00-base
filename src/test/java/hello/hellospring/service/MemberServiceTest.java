package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService ;
    MemoryMemberRepository memberRepository;

   @BeforeEach
   void beforeEach(){
       memberRepository = new MemoryMemberRepository();
       memberService = new MemberService(memberRepository);
   }

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("죽석");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("죽석");
        Member member2 = new Member();
        member2.setName("죽석");

        //when
        memberService.join(member1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}