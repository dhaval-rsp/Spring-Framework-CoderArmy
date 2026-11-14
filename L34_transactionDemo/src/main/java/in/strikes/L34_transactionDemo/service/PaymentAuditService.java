package in.strikes.L34_transactionDemo.service;

import in.strikes.L34_transactionDemo.entity.Order;
import in.strikes.L34_transactionDemo.entity.PaymentAudit;
import in.strikes.L34_transactionDemo.repository.PaymentAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentAuditService {

    private PaymentAuditRepository paymentAuditRepository;

    public PaymentAuditService(PaymentAuditRepository paymentAuditRepository) {
        this.paymentAuditRepository = paymentAuditRepository;
    }

    @Transactional(
            propagation = Propagation.REQUIRED
    )
    public void audit(Order order) {
        PaymentAudit paymentAudit =
                new PaymentAudit(order.getAmount(), order.getId(), true);

        paymentAuditRepository.save(paymentAudit);

        try{
            throw new RuntimeException("Some Error Occurred in audit");
        }
        catch (RuntimeException e) {}
    }
}
