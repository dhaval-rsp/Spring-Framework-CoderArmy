package in.strikes.L22_aopIntroductionDemo.service;

import in.strikes.L22_aopIntroductionDemo.dto.Student;
import org.springframework.stereotype.Component;

@Component
public class LoggingDecorator implements StudentService {

    private StudentServiceImpl studentServiceImpl;

    public LoggingDecorator(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @Override
    public void createStudent(Student student) {

        // Logging related logic

        System.out.println("3. --> Entered in the LoggingDecorator");
        LoggingServiceUtil.logStart("StudentServiceImpl", "createStudent");

        System.out.println("4. --> Calling studentServiceImpl from LoggingDecorator");
        studentServiceImpl.createStudent(student);

        LoggingServiceUtil.logEnd("StudentServiceImpl", "createStudent");

        System.out.println("10. --> Leaving the LoggingDecorator");


    }
}
