package in.strikes.L22_aopIntroductionDemo.repository;

import in.strikes.L22_aopIntroductionDemo.dto.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    public void save(Student student) {
        System.out.println("7. --> Entered in the StudentRepository");

        System.out.println("Student Saved");

        System.out.println("8. --> Leaving the StudentRepository");
    }
}
