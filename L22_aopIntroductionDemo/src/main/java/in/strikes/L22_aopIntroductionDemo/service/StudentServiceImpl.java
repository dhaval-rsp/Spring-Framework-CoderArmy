package in.strikes.L22_aopIntroductionDemo.service;

import in.strikes.L22_aopIntroductionDemo.dto.Student;
import in.strikes.L22_aopIntroductionDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student){

        System.out.println("5. --> Entered in the StudentServiceImpl");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("6. --> Calling studentRepository from StudentServiceImpl");
        studentRepository.save(student);

        System.out.println("9. --> Leaving the StudentServiceImpl");
    }
}
