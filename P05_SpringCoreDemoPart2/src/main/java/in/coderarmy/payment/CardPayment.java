package in.coderarmy.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.*;

@Component
@Qualifier("card")
public class CardPayment implements  PaymentService {
    @Override
    public void pay() {
        System.out.println("Paying via card");
    }
}
