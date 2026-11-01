package in.strikes.L20_filterAdvance.service;


import in.strikes.L20_filterAdvance.dto.Student;
import in.strikes.L20_filterAdvance.dto.StudentResponseDto;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public StudentResponseDto createStudent(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setName(student.getName());
        studentResponseDto.setMessage("Student is saved successfully");

        return studentResponseDto;
    }
}
