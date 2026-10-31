package in.strikes.L19_filterDemo.service;


import in.strikes.L19_filterDemo.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public void createStudent(Student student) {
        System.out.println("Student Created");
        System.out.println(student.getEmail());
        System.out.println(student.getName());

        try {
            Thread.sleep(2000);
        }
        catch(Exception e){}
    }
}
