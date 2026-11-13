package in.strikes.L33_tansactionDemo.repository;

import in.strikes.L33_tansactionDemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account, Long> {
}
