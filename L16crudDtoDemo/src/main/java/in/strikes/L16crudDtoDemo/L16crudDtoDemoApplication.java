package in.strikes.L16crudDtoDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
		//(exclude = DataSourceAutoConfiguration.class)
public class L16crudDtoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(L16crudDtoDemoApplication.class, args);

		System.out.println("Hello Java");
	}

}

/*
Browser / Postman
       ↓
[CONTROLLER LAYER] StudentController
              ↓
[DTO LAYER] CreateStudentRequestDto
              ↓
[SERVICE LAYER] StudentService.createStudent()
              ↓
[SERVICE LAYER] mapToEntity()
              ↓
[ENTITY LAYER] Student Entity
              ↓
[REPOSITORY LAYER CALLED FROM SERVICE] StudentRepository.save()
              ↓
           Database
              ↓
[ENTITY LAYER] Student Entity (saved)
              ↓
[SERVICE LAYER] mapToDto()
              ↓
[SERVICE LAYER] CreateStudentResponseDto
              ↓
[CONTROLLER LAYER] StudentController
              ↓
         JSON Response
              ↓
       Browser / Postman

 */