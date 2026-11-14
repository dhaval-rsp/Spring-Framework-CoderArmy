package in.strikes.L34_transactionDemo.service;

import in.strikes.L34_transactionDemo.entity.Order;
import in.strikes.L34_transactionDemo.repository.OrderRepository;
import in.strikes.L34_transactionDemo.repository.PaymentAuditRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private PaymentAuditService paymentAuditService;

    public OrderService(OrderRepository orderRepository,
                        PaymentAuditService paymentAuditService) {
        this.orderRepository = orderRepository;
        this.paymentAuditService = paymentAuditService;
    }

    @Transactional
    public void placeOrder(Order order) {

        orderRepository.save(order);

        try {
            paymentAuditService.audit(order);
        }
        catch (Exception e) {

        }
        //throw new RuntimeException("Some Error Occurred");
    }
}
