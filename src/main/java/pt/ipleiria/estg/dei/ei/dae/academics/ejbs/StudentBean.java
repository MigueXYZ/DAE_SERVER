package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.util.List;

@Stateless
public class StudentBean {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(String username, String password, String name, String email, long course) {
        //check if course exists using find method from CourseBean
        Course courseFound = entityManager.find(Course.class, course);
        if (courseFound == null) {
            throw new IllegalArgumentException("Course " + course + " not found");
        }
        Student student = new Student(username, password, name, email, courseFound);
        courseFound.addStudent(student);
        entityManager.persist(student);
    }

    public List<Student> findAll() {
        // remember, maps to: “SELECT s FROM Student s ORDER BY s.name”
        return entityManager.createNamedQuery("getAllStudents", Student.class).getResultList();
    }

    public Student find(String username){
        var student = entityManager.find(Student.class, username);
        if(student==null){
            throw new IllegalArgumentException("Student "+username+" not found");
        }
        return student;
    }

}
