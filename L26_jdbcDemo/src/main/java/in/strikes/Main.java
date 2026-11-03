package in.strikes;

import in.strikes.model.Student;
import in.strikes.repository.StudentRepository;
import in.strikes.repository.StudentRepositoryNew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("1. --> Entered into Main method");

        System.out.println("2. --> Calling StudentRepository.createUser method from Main");

        //StudentRepository studentRepository  = new StudentRepository();
        StudentRepositoryNew studentRepositoryNew  = new StudentRepositoryNew();

        //studentRepository.createStudent(new Student("Jatin", "jatin@gmail.com", 22));
        //studentRepositoryNew.createStudent(new Student("Gillu", "gillu218@gmail.com", 26));

        //studentRepository.updateStudent();
        //studentRepository.deleteStudent();
        //studentRepository.getStudentById();

        studentRepositoryNew.getAllStudent();
    }
}


// url, username, password
// jdbc:mysql://localhost:3306/