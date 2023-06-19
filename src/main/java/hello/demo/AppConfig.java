package hello.demo;

import hello.demo.discount.DiscountPolicy;
import hello.demo.discount.FixDiscountPolicy;
import hello.demo.discount.RateDiscountPolicy;
import hello.demo.member.MemberRepository;
import hello.demo.member.MemberService;
import hello.demo.member.MemberServiceImpl;
import hello.demo.member.MemoryMemberRepository;
import hello.demo.order.OrderService;
import hello.demo.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {//Application의 구성 역할 (구성 영역은 당연히 요구사항 변경 시 변경된다. 잘 구분하면 구현 영역은 변경하지 않아도 된다.)

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();//역할과 구현이 잘 분리되어 있으면, 정액이 아닌 정률 할인으로 바꿀 때 이것만 수정하면 됨
        return new RateDiscountPolicy();
    }
}
