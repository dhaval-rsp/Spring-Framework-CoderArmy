package in.strikes.L36_springSecurityDemo.repository;

import in.strikes.L36_springSecurityDemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
