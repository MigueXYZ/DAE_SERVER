package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

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

    //enrollStudentInSubject
    public void enrollStudentInSubject(String username, long subjectCode){
        Student student = find(username);
        //check if subject exists using find method from SubjectBean
        Subject subject = entityManager.find(Subject.class, subjectCode);
        if (subject == null) {
            throw new IllegalArgumentException("Subject " + subjectCode + " not found");
        }
        student.addSubject(subject);
    }

}
