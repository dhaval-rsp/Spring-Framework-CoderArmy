package in.coderarmy;

import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        // Up the IOC container using Annotation based configuration, and rules are defined under Appconfig file
        // AppConfig files tells for which class we need to create object
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        OrderService o = context.getBean(OrderService.class);
        o.placeOrder();

        User user = context.getBean(User.class);
        System.out.println(user.getName());

    }
}