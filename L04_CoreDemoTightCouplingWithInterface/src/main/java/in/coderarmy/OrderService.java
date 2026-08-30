package in.coderarmy;

import in.coderarmy.notification.*;

public class OrderService {

    NotificationService n = new PopUpNotificationService();

    public void placeOrder(){
        System.out.println("Order Placed");
        n.sendNotification();
    }
}
