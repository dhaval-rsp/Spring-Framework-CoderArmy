package in.strikes.L27_springJDBCDemo.service;

import in.strikes.L27_springJDBCDemo.model.Student;
import in.strikes.L27_springJDBCDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepositor;

    public StudentService(StudentRepository studentRepositor) {
        this.studentRepositor = studentRepositor;
    }

    public void createStudent(Student student) {
        studentRepositor.createStudent(student);
    }

    public void updateStudent(Student student) {
        studentRepositor.updateStudent(student, student.getId());
    }

    public void deleteStudent(Long id) {
        studentRepositor.deleteStudent(id);
    }

    public Student getStudentById(Long id) {
        return studentRepositor.getStudentById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepositor.getAllStudents();
    }
}
