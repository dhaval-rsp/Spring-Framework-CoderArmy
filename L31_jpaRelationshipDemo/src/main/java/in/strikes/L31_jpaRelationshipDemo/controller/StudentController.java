package in.strikes.L31_jpaRelationshipDemo.controller;

import in.strikes.L31_jpaRelationshipDemo.model.Student;
import in.strikes.L31_jpaRelationshipDemo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent(
            @RequestBody Student student) {

        studentService.createStudent(student);
        return ResponseEntity.ok("CREATED STUDENT");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(
            @PathVariable Long id) {
        Student student = studentService.fetchStudentById(id);
        return ResponseEntity.ok(student);
    }
}
