package hello.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//Component를 하면 자동으로 스프링 빈이 등록이 되는데, 의존 관계 주입은 할 방법이 없다. 그래서 @AutoWired를 사용하게 된다.
@Component
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
