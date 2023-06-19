package hello.demo;

import hello.demo.member.Grade;
import hello.demo.member.Member;
import hello.demo.member.MemberService;
import hello.demo.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();//DI(Dependency Injection) 의존관계 주입 전

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();//DI(Dependency Injection) 의존관계 주입 후, spring annotation 사용 전

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
