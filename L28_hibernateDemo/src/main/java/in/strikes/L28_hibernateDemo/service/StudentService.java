package in.strikes.L28_hibernateDemo.service;

import in.strikes.L28_hibernateDemo.model.Student;
import in.strikes.L28_hibernateDemo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public Student getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void updateStudent(Student s, Long id) {
        Student student1 = studentRepository.findById(id);

        if (student1 == null) {
            throw new RuntimeException("Student not found");
        }

        student1.setName(s.getName());
        student1.setAge(s.getAge());
        student1.setEmail(s.getEmail());
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id);

        if (student == null) {
            throw new RuntimeException("Student not found");
        }

        studentRepository.remove(student);
    }
}
