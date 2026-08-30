package in.coderarmy;

import in.coderarmy.notification.*;

public class Main {
    public static void main(String[] args) {

        NotificationService n = new EmailService(); // Main Method creating Notification Service

        System.out.println("-----------------------------------");

        OrderService o = new OrderService(n); // Main Method creating Order Service
        o.placeOrder();

        System.out.println("-----------------------------------");

        OrderService or = new OrderService(); // Main Method creating Order Service
        or.setNotification(n);
        or.placeOrder();

        System.out.println("-----------------------------------");

        // Field injection: directly assign the dependency
        OrderService os = new OrderService(); // Main Method creating Order Service
        os.notification = n;   // notification field from OrderService is public so directly manipulating it
        os.placeOrder();

    }
}

/*
- Basically Object creation is done by main method,
- the business logic class like OrderService & Notification Service doing their work not creating any objects

- Main creates both objects then passes object of notification to order service by means of Constructor / Setter / public field

- whenever we need to update notification type just change main method no need to update in Notification classes
- Since multiple types of notifcation we want thats why we used Interface NotificationService which get implemented by diff diff types of services
 */
