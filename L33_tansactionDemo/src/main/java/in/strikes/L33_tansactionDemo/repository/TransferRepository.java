package in.strikes.L33_tansactionDemo.repository;

import in.strikes.L33_tansactionDemo.entity.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferRecord, Long> {
}
