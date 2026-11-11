package in.strikes.L31_jpaRelationshipDemo.service;

import in.strikes.L31_jpaRelationshipDemo.model.Department;
import in.strikes.L31_jpaRelationshipDemo.model.Student;
import in.strikes.L31_jpaRelationshipDemo.repository.DepartmentRepository;
import in.strikes.L31_jpaRelationshipDemo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    private StudentRepository studentRepository;

    public DepartmentService(DepartmentRepository departmentRepository,
                             StudentRepository studentRepository) {
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void createDepartment(Department department){
        Student s1 = new Student();
        s1.setName("Aditya");
        s1.setDepartment(department);

        Student s2 = new Student();
        s2.setName("Rohit");
        s2.setDepartment(department);

        Student s3 = new Student();
        s3.setName("Rohan");
        s3.setDepartment(department);

        Student s4 = new Student();
        s4.setName("Sonika");
        s4.setDepartment(department);

        //department.getStudents().addAll(List.of(s1, s2, s3, s4));

        departmentRepository.save(department);

    }

    @Transactional
    public void removeDepartment(Long deptId) {

        Department department = departmentRepository.findById(deptId);

        departmentRepository.removeDepartment(department);
    }
}
