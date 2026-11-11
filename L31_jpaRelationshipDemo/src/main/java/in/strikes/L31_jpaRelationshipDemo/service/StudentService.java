package in.strikes.L31_jpaRelationshipDemo.service;

import in.strikes.L31_jpaRelationshipDemo.model.Department;
import in.strikes.L31_jpaRelationshipDemo.model.Profile;
import in.strikes.L31_jpaRelationshipDemo.model.Student;
import in.strikes.L31_jpaRelationshipDemo.repository.DepartmentRepository;
import in.strikes.L31_jpaRelationshipDemo.repository.ProfileRepository;
import in.strikes.L31_jpaRelationshipDemo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private DepartmentRepository departmentRepository;
    private ProfileRepository profileRepository;

    public StudentService(StudentRepository studentRepository,
                          DepartmentRepository departmentRepository,
                          ProfileRepository profileRepository) {

        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.profileRepository = profileRepository;
    }

    @Transactional
    public void createStudent(Student student) {

        Department department = new Department();
        department.setName("CSE");

        Profile profile = new Profile();
        profile.setBio("Simple Bio");

        student.setDepartment(department);
        student.setProfile(profile);

        departmentRepository.save(department);
        profileRepository.save(profile);
        studentRepository.save(student);

    }

    public Student fetchStudentById(Long id) {

        Student s1 =  studentRepository.findById(id);
        System.out.println("Laziliy Fetched student");

        Department d1 = s1.getDepartment();
        System.out.println("Laziliy Fetched department");

        Profile p1 = s1.getProfile();
        System.out.println("Laziliy Fetched profile");

        return s1;
    }
}
