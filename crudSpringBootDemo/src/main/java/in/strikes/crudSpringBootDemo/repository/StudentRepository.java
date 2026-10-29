package in.strikes.crudSpringBootDemo.repository;

import in.strikes.crudSpringBootDemo.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    public Student saveStudent(Student studentReq){
        // save to DB.
        System.out.println("Inside Student Repository");
        System.out.println("Exiting Student Repository");
        return  null;
    }
}