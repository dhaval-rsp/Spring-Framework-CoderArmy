package in.strikes.L34_transactionDemo.repository;

import in.strikes.L34_transactionDemo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
