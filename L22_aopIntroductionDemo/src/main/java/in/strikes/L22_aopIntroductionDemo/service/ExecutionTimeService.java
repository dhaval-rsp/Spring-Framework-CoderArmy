package in.strikes.L22_aopIntroductionDemo.service;

import in.strikes.L22_aopIntroductionDemo.dto.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ExecutionTimeService implements  StudentService {

    private LoggingDecorator loggingDecorator;

    public ExecutionTimeService(LoggingDecorator loggingDecorator) {
        this.loggingDecorator = loggingDecorator;
    }

    @Override
    public void createStudent(Student student) {

        System.out.println("1. --> Entered in the ExecutionTimeService");
        long start = System.currentTimeMillis();

        System.out.println("2. --> Calling loggingDecorator from ExecutionTimeService");
        loggingDecorator.createStudent(student);

        long end = System.currentTimeMillis();

        System.out.println("Total Time: " +  (end - start));

        System.out.println("11. --> Leaving the ExecutionTimeService");

    }
}
