package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //전체적
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //회원가입
    @Transactional //이건 쓰기 할거라 붙힘 FALSE 취급
    public Long join(Member member) {
        //중복회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    //회원 전체 조회
    //@Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    //@Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}