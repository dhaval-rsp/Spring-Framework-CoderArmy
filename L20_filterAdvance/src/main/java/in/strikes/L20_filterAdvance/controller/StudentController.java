package in.strikes.L20_filterAdvance.controller;

import in.strikes.L20_filterAdvance.dto.Student;
import in.strikes.L20_filterAdvance.dto.StudentResponseDto;
import in.strikes.L20_filterAdvance.service.StudentService;
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
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody Student Student) {
        StudentResponseDto studentResponseDto =
                studentService.createStudent(Student);
        return ResponseEntity.ok(studentResponseDto);
    }

}
