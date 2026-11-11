package in.strikes.L31_jpaRelationshipDemo.controller;

import in.strikes.L31_jpaRelationshipDemo.model.Department;
import in.strikes.L31_jpaRelationshipDemo.service.DepartmentService;
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
        return ResponseEntity.ok("Department Created");
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteDepartment(@PathVariable Long id) {

        departmentService.removeDepartment(id);
        return ResponseEntity.ok("Department Removed");
    }
}
