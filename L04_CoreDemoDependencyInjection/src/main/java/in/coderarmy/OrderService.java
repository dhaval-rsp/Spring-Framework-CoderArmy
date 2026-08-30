package in.coderarmy;

import in.coderarmy.notification.*;

public class OrderService {

    public NotificationService notification;

    public OrderService() {
    }

    public OrderService(NotificationService notification) {
        this.notification = notification;
        System.out.println("DI through Constructor");
    }

    public void setNotification(NotificationService notification) {
        this.notification = notification;
        System.out.println("DI through Setter");
    }

    public void placeOrder(){
        System.out.println("Order Placed");
        notification.sendNotification();
    }
}
