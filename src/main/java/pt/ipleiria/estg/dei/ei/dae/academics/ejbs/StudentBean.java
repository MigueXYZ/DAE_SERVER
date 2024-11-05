package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
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
        if(student==null){
            throw new IllegalArgumentException("Student "+username+" not found");
        }
        Subject subject = entityManager.find(Subject.class, subjectCode);
        if (subject == null) {
            throw new IllegalArgumentException("Subject " + subjectCode + " not found");
        }
        student.addSubject(subject);
        subject.addStudent(student);
        entityManager.persist(student);
        entityManager.persist(subject);

        //check if student is now in subject
        if(!subject.getStudents().contains(student)){
            throw new IllegalArgumentException("Student "+username+" not enrolled in subject "+subjectCode);
        }

        //persist changes
        entityManager.flush();
    }

    public Student findWithSubjects(String username){
        var student = this.find(username);
        Hibernate.initialize(student.getSubjects());
        return student;
    }

    //unenrollStudentInSubject
    public void unenrollStudentInSubject(String username, long subjectCode){
        Student student = find(username);
        //check if subject exists using find method from SubjectBean
        if (student == null) {
            throw new IllegalArgumentException("Student " + username + " not found");
        }
        Subject subject = entityManager.find(Subject.class, subjectCode);
        if (subject == null) {
            throw new IllegalArgumentException("Subject " + subjectCode + " not found");
        }
        student.removeSubject(subject);
        subject.removeStudent(student);
        //apply changes
        entityManager.merge(student);
        entityManager.merge(subject);

        //check if student is now in subject
        if(subject.getStudents().contains(student)){
            throw new IllegalArgumentException("Student "+username+" still enrolled in subject "+subjectCode);
        }

        //check if subject is now in student
        if(student.getSubjects().contains(subject)){
            throw new IllegalArgumentException("Subject "+subjectCode+" still enrolled in student "+username);
        }

        //persist changes
        entityManager.flush();

    }

    public void delete(String username){
        Student student = find(username);
        entityManager.remove(student);
    }

    //update
    public void update(String username, String password, String name, String email, long courseCode){
        Student student = find(username);
        student.setPassword(password);
        student.setName(name);
        student.setEmail(email);
        Course course = entityManager.find(Course.class, courseCode);
        if (course == null) {
            throw new IllegalArgumentException("Course " + courseCode + " not found");
        }
        student.setCourse(course);
        entityManager.merge(student);
    }



}
