package in.strikes.L16crudDtoDemo.repository;

import in.strikes.L16crudDtoDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
| Repository is an interface, not a class.
| Why?
| Because Spring Data JPA creates the implementation for us automatically.
|
| ----------- What is JpaRepository? -----------
| JpaRepository <Student, Long>
|       Create a repository for Student entity
|       whose Primary Key datatype is Long
|
| ---------- Because your entity probably looks like: ----------
    @Entity
    public class Student {

        @Id
        @GeneratedValue
        private Long id;
    }
|
| ----------- What Spring Gives Automatically -----------
| Just by writing:- extends JpaRepository<Student, Long>
| We will automatically get below methods without writing anything ourself.
|   save()
|   findById()
|   findAll()
|   delete()
|   deleteById()
|   count()
|   existsById()
|
| ----------- Visual Understanding -----------
| StudentRepository >> JpaRepository >> Spring Data JPA >> Hibernate >> MySQL
| When we write studentRepository.save(student) we think Repository.save() is called but,
| Internally :- save() >> JpaRepository >> Hibernate >> SQL Query >> Database
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndDeletedIsFalse (Long id);

    List<Student> findByDeletedIsFalse();

    Boolean existsByEmail(String emailId);
}

/*
| ------------------------ Method 1 ------------------------
| ----------- findByIdAndDeletedIsFalse (Long id) -----------
|
| This is called Derived Query Method.
| Spring creates query from method name.
| Return Type :- Optional<Student>
| Meaning :- Maybe Student exists or doesn't exist
| findyBy - search record | Id - the id which need to check | And - adding more condition | Deleted - the delete column | IsFalse - deleted column is false or not
 */

/*
Service Layer = "What should happen?"
Repository Layer = "How to get/store data in database?"

StudentRepository
│
├── save(student)
│      → INSERT / UPDATE
│
├── findById(id)
│      → fetch one student
│
├── delete(student)
│      → DELETE row
│
├── findByDeletedIsFalse()
│      → get active students
│
├── findByIdAndDeletedIsFalse(id)
│      → get one active student
│
└── existsByEmail(email)
       → check duplicate email
 */