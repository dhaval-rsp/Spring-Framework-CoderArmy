package in.strikes.L27_springJDBCDemo.controller;

import in.strikes.L27_springJDBCDemo.model.Student;
import in.strikes.L27_springJDBCDemo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent (@RequestBody Student student) {
        studentService.createStudent(student);
        return ResponseEntity.ok(student + "created");
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        return ResponseEntity.ok("DONE");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("DONE");
    }

}
