package in.coderarmy;

import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    public PaymentService() {
        System.out.println("PaymentService Constructor - Eager loading will print this");
    }

    public void pay(){
        System.out.println("Payment done");
    }
}
