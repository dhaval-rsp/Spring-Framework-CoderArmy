package in.strike.L23_aopDemo.service;

import in.strike.L23_aopDemo.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public Student createStudent(Student student) {
        System.out.println("Student saved");

        return student;
    }

    public String dummyMethod(String s){
        System.out.println("dummyMethod called");
        return s;
    }
}
