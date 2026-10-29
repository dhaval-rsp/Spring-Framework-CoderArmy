package in.strikes.crudSpringBootDemo_full.controller;

import in.strikes.crudSpringBootDemo_full.entity.Student;
import in.strikes.crudSpringBootDemo_full.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")

public class StudentController {

    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){

        Student createStudent = studentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createStudent);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Student studentResp = studentService.getStudent(id);

        if(studentResp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }

    @GetMapping("/getAll")
        public ResponseEntity<List<Student>> getAllStudent(){
            List<Student> studentList = studentService.getAllStudent();

            if(studentList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(studentList);
        }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestBody Student studentReq){
        Student studentResp = studentService.updateStudent(id, studentReq);

        if(studentResp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        boolean isDeleted = studentService.deleteStudent(id);

        if(!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Record deleted");
    }
}
