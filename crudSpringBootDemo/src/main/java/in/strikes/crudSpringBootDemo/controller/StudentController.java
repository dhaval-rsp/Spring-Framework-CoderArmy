package in.strikes.crudSpringBootDemo.controller;

import in.strikes.crudSpringBootDemo.entity.Student;
import in.strikes.crudSpringBootDemo.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")

public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // create student
    @PostMapping("/create")
    public String createStudent(@RequestBody Student student){

        System.out.println("Inside Student Controller");
        Student createStudent = studentService.createStudent(student);
        System.out.println("Exiting Student Controller");
        return "Student created";
    }

}
