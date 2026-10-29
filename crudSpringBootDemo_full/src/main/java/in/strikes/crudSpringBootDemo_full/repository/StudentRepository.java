package in.strikes.crudSpringBootDemo_full.repository;

import in.strikes.crudSpringBootDemo_full.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
