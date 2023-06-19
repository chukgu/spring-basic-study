package hello.demo;

import hello.demo.member.Grade;
import hello.demo.member.Member;
import hello.demo.member.MemberService;
import hello.demo.order.Order;
import hello.demo.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();//DI(Dependency Injection) 의존관계 주입 전

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();//DI(Dependency Injection) 의존관계 주입 후, spring annotation 사용 전

        //ApplicationContext - 스프링 컨테이너
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
