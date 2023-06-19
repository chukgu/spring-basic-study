package hello.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(//@Component 가 붙은 애들을 다 찾아서 스프링 빈에 모두 등록해준다.
        //basePackages = "hello.demo",//componentscan 시작 대상
        //-> 지정하지 않는 경우에는 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작위치가 되기 때문에
        //   권장 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치(AutoAppConfig) 를 프로젝트 최상단에 두는 것
        //-> 참고로 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication 이 프로젝트 시작 루트 위치에 두는 것이 관례 (@SpringBootApplication 안에 @ComponentScan 이 들어있음)
        //   그러면 ComponentScan 을 안 써도 됨

        //기본 스캔 대상
        //@ComponentScan, @Controller, @Service, @Repository, @Configuration
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)//AppConfig 와 같은 @Configuration 제외(기존꺼 공부용으로 남기기 위해)
)
public class AutoAppConfig {

}
