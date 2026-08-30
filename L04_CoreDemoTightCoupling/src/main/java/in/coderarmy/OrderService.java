package in.coderarmy;

public class OrderService {

    EmailService notification = new EmailService();

    public void placeOrder(){
        System.out.println("Order Placed");
        notification.sendNotification();
    }
}
