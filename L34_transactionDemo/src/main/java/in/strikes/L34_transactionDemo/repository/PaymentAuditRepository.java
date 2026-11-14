package in.strikes.L34_transactionDemo.repository;

import in.strikes.L34_transactionDemo.entity.PaymentAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentAuditRepository extends JpaRepository<PaymentAudit, Long> {
}
