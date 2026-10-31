package in.strikes.L19_filterDemo.controller;

import in.strikes.L19_filterDemo.dto.Student;
import in.strikes.L19_filterDemo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student Student) {
        studentService.createStudent(Student);
        return ResponseEntity.ok("DONE");
    }

}
