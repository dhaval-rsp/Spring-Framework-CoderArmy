package in.strikes.L30_jpaRelationshipDemo.controller;

import in.strikes.L30_jpaRelationshipDemo.model.Department;
import in.strikes.L30_jpaRelationshipDemo.model.Student;
import in.strikes.L30_jpaRelationshipDemo.repository.DepartmentRepository;
import in.strikes.L30_jpaRelationshipDemo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<String> createStudent(
            @RequestBody Student student,
            @RequestParam Long deptId) {

        studentService.createStudent(student, deptId);
        return ResponseEntity.ok("Student " +  student.getName() + " added");
    }

    @PostMapping("/withDepartment")
    public ResponseEntity<String> createStudent(
            @RequestBody Student student,
            @RequestParam String deptName) {

        studentService.createStudent(student, deptName);
        return ResponseEntity.ok("Student " +  student.getName());
    }
}
