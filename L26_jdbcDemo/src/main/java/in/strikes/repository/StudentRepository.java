package in.strikes.repository;

import com.mysql.cj.protocol.Resultset;
import in.strikes.model.Student;

import java.sql.*;

public class StudentRepository {

    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "root";

    public StudentRepository() {
        System.out.println("3. --> Entered into StudentRepository");
        System.out.println("4. --> StudentRepository object created");
    }

    public void createStudent (Student student){
        try {
            // DriverManager a utility class coming from mysql driver
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = """
                    INSERT INTO students (name, email, age)
                    VALUES ('%s', '%s', '%d')
                    """
                    .formatted(student.getName(),
                    student.getEmail(),
                    student.getAge());

            int result = statement.executeUpdate(sql); // 0 - operation failed 1 - operation successful

            if (result == 1 ){
                System.out.println("Create operation successfuly");
            }
            else{
                System.out.println("Create operation failed");
            }

            connection.close();
        }
        catch(SQLException e){
            System.out.println("       Database connection failed");
            e.printStackTrace();
        }
    }

    public void updateStudent (){
        try {
            // DriverManager a utility class coming from mysql driver
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("       Database connected successfully");

            Statement statement = connection.createStatement();

            String sql = "UPDATE students SET age = 30 " +
                    "WHERE id = 1";

            int resultSet = statement.executeUpdate(sql);

            if (resultSet == 1 ){
                System.out.println("Update operation successfuly");
            }
            else{
                System.out.println("Update operation failed");
            }

            connection.close();
        }
        catch(SQLException e){
            System.out.println("       Database connection failed");
            e.printStackTrace();
        }
    }

    public void deleteStudent (){
        try {
            // DriverManager a utility class coming from mysql driver
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("       Database connected successfully");

            Statement statement = connection.createStatement();

            String sql = "DELETE FROM students " +
                    "WHERE id = 1";

            int result = statement.executeUpdate(sql); // 0 - operation failed 1 - operation successful

            if (result == 1 ){
                System.out.println("Delete operation successfuly");
            }
            else{
                System.out.println("Delete operation failed");
            }

            connection.close();
        }
        catch(SQLException e){
            System.out.println("       Database connection failed");
            e.printStackTrace();
        }
    }

    public void getStudentById() {
        try {
            // DriverManager a utility class coming from mysql driver
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("       Database connected successfully");

            Statement statement = connection.createStatement();

            String sql = "SELECT id, name, email, age " +
                    "FROM students WHERE id = 2";

            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("       Executing select query");
            resultSet.next();

            Student student = mapRow(resultSet);

            System.out.println(student);

            connection.close();
        }
        catch(SQLException e){
            System.out.println("       Database connection failed");
            e.printStackTrace();
        }
    }

    public void completeCRUD() {
        try {
            // DriverManager a utility class coming from mysql driver
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = "";

            boolean result = statement.execute(sql);

            if(result){
                ResultSet resultSet = statement.getResultSet();
            }
            else {
                int rowAffected = statement.getUpdateCount();
            }

            connection.close();
        }
        catch(SQLException e){
            System.out.println("       Database connection failed");
            e.printStackTrace();
        }
    }

    private Student mapRow(ResultSet r) throws SQLException {
        Student student = new Student();

        student.setId(r.getLong("id"));
        student.setName(r.getString("name"));
        student.setEmail(r.getString("email"));
        student.setAge(r.getInt("age"));

        return student;
    }

}
