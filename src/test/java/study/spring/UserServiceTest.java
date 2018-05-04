package study.spring;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: java-study
 * @description: 用户业务逻辑类测试类
 * @author: wangrui45
 * @create: 2018-05-04 10:58
 **/

public class UserServiceTest {
    @Test
    public void testSayHello(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService myUser = (UserService) context.getBean("myUser");
        myUser.sayHello();
    }

}
