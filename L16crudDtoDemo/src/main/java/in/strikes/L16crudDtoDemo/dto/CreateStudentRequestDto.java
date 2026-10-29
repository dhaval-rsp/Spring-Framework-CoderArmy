package in.strikes.L16crudDtoDemo.dto;

import jakarta.validation.constraints.*;

// Request DTO - receives data from client side [browser/postman/application ui]

/*
CreateStudentRequestDto is a Request DTO used to receive student data from the client.
It contains validation rules using Jakarta Validation annotations such as @NotBlank, @NotNull, @Email, and @Min.
After successful validation, the DTO is converted into a Student entity using mapToEntity() and then saved to the database.
 */

public class CreateStudentRequestDto {

    @NotBlank(message = "Name cannot be null/Empty or blank")
    @Size(min = 2, max = 50, message = "Student name must be within 2 to 50 character long")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Student must be atleast 18 years old")
    private Integer age;

    @NotBlank(message = "Student email cannot be blank")
    @Email(message = "Student email must be valid")
    private String email;

    @NotNull(message = "RollNo is required")
    private Integer rollNo;

    @NotBlank(message = "Subject is required")
    private String subject;

    // GETTER & SETTER
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
