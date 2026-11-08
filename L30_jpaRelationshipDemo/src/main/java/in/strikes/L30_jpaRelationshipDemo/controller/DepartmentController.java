package in.strikes.L30_jpaRelationshipDemo.controller;

import in.strikes.L30_jpaRelationshipDemo.model.Department;
import in.strikes.L30_jpaRelationshipDemo.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(
            @RequestBody Department department) {

        departmentService.createDepartment(department);
        return ResponseEntity.ok("DONE");
    }

    @PostMapping("/withStudent")
    public ResponseEntity<String> createDepartment(
            @RequestBody Department department,
            @RequestParam String studentName) {

        departmentService.createDepartment(department, studentName);
        return ResponseEntity.ok("DONE department created" + department.getName());
    }
}
