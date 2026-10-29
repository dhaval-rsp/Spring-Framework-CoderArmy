package in.strikes.L16crudDtoDemo.controller;

import in.strikes.L16crudDtoDemo.dto.CreateStudentResponseDto;
import in.strikes.L16crudDtoDemo.dto.CreateStudentRequestDto;
import in.strikes.L16crudDtoDemo.dto.UpdateStudentResponseDto;
import in.strikes.L16crudDtoDemo.dto.UpdateStudentRequestDto;
import in.strikes.L16crudDtoDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/students")
public class StudentController {

    // instance variable for service layer
    private StudentService studentService;

    // injecting service layer using constructor + autowire feature of spring
    public StudentController(StudentService studentService){
        this.studentService = studentService; // this is similar to StudentService studentService = new StudentService();
    }

    // calling createStudent method from service layer [argument takes - CreateStudentRequestDto object]
    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(
            @Valid @RequestBody CreateStudentRequestDto studentRequestDto) {

        CreateStudentResponseDto createdStudent =
                studentService.createStudent(studentRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    // calling getStudent method from service layer
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id) {
        CreateStudentResponseDto studentResp = studentService.getStudent(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }

    // calling getAllStudent method from service layer
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudent() {
        List<CreateStudentResponseDto> studentList = studentService.getAllStudent();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentList);
    }

    // calling updateStudent method from service layer
    @PutMapping
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@RequestParam Long id,
                                                                  @RequestBody UpdateStudentRequestDto studentReq) {
        UpdateStudentResponseDto studentResp =
                studentService.updateStudent(id, studentReq);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }

    // calling deleteStudent method from service layer
    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@RequestParam Long id) {
        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    // calling deleteStudentSoftly method from service layer
    @PatchMapping("/delete-soft")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id) {
        studentService.deleteStudentSoftly(id);

        return ResponseEntity.noContent().build();
    }
}
