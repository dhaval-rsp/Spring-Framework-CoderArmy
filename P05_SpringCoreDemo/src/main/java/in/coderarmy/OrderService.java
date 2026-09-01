package in.coderarmy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private PaymentService paymentService;

    public OrderService() {
    }

    @Autowired
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
        System.out.println("OrderService Constructor - Eager loading will print this");
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
        System.out.println("OrderService Setter - Eager loading will print this");
    }

    public void placeOrder(){
        paymentService.pay();
        System.out.println("Order placed");
    }
}
