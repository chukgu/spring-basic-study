package hello.demo.singleton;

import hello.demo.AppConfig;
import hello.demo.member.MemberRepository;
import hello.demo.member.MemberServiceImpl;
import hello.demo.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        //3개 다 동일함
        //-> memberRepository 를 3번 객체 생성되는 게 아니라, 1번만 호출 됨
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        //출력하면 SpringCGLIB가 붙으면서 내가 만든 클래스가 아닌 스프링이 만든 걸로 볼 수 있다.
        //@Bean 이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반화하는 코드가 동적으로 이루어짐
        //-> 따라서 싱글톤이 보장되는 것
        //만약 @Configuration 을 주석처리하면, 원래 예상하던 bean = class hello.demo.AppConfig(CGLIB X) 로 출력되면서 memberRepository가 3번 출력된다.
        System.out.println("bean = " + bean.getClass());
    }
}
