package in.strikes.L29_hibernateInternalsDemo.controller;

import in.strikes.L29_hibernateInternalsDemo.model.Student;
import in.strikes.L29_hibernateInternalsDemo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return ResponseEntity.ok("Created Student " + student.getName() + " with id " + student.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent (@RequestBody Student student,
                                                 @PathVariable Long id) {
        studentService.updateStudent(student, id);
        return ResponseEntity.ok("Donnne");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Deleted the " + studentService.getStudentById(id).getName());
    }
}
