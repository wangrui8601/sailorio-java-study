package study.spring;

/**
 * @program: java-study
 * @description: 用户业务逻辑类
 * @author: wangrui45
 * @create: 2018-05-04 10:50
 **/

public class UserService {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello(){
        System.out.println("hello " + this.name);
    }
}
