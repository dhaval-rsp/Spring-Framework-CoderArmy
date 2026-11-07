package in.strikes.L30_jpaRelationshipDemo.service;

import in.strikes.L30_jpaRelationshipDemo.model.Department;
import in.strikes.L30_jpaRelationshipDemo.model.Student;
import in.strikes.L30_jpaRelationshipDemo.repository.DepartmentRepository;
import in.strikes.L30_jpaRelationshipDemo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private StudentRepository studentRepository;

    public DepartmentService(
            DepartmentRepository departmentRepository,
            StudentRepository studentRepository) {
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void createDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Transactional
    public void createDepartment(
            Department department,
            String studentName
    ) {
        Student student = new Student();
        student.setName(studentName);
        student.setDepartment(department);

        department.getStudents().add(student);
        departmentRepository.save(department);
    }
}
