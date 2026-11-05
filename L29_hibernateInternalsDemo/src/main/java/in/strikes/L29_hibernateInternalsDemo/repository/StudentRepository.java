package in.strikes.L29_hibernateInternalsDemo.repository;

import in.strikes.L29_hibernateInternalsDemo.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // SAVE
    public void save(Student student) {
        entityManager.persist(student);
    }

    public void saveAll(List<Student> studentList) {

        int counter = 0;

        for(Student student : studentList) {

            entityManager.persist(student);
            counter++;

            if (counter % 100 == 0) {
                entityManager.flush();
                entityManager.clear();
                entityManager.refresh(student);
            }
        }
    }

    // READ
    public Student findById(Long id) {
        Student s1 = entityManager.find(Student.class, id);
        return s1;
    }

    // DELETE
    public void remove(Student student) {
        entityManager.remove(student);
    }

    public void detach(Student student) {
        entityManager.detach(student);
    }

    public Student attachAgain(Student student) {
        return entityManager.merge(student);
    }

    public void flush() {
        entityManager.flush();
    }
}
