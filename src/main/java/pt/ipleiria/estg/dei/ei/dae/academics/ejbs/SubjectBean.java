package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import java.util.List;

@Stateless
public class SubjectBean {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(long code, String name, String schoolYear, int courseYear, long courseCode) {
        //check if course exists using find method from CourseBean
        Course course = entityManager.find(Course.class, courseCode);
        if (course == null) {
            throw new IllegalArgumentException("Course " + courseCode + " not found");
        }
        Subject subject = new Subject(code, name, schoolYear, courseYear, course);
        course.addSubject(subject);
        entityManager.persist(subject);
    }

    //find all
    public List<Subject> findAll() {
        // remember, maps to: “SELECT s FROM Subject s JOIN s.course c ORDER BY c.name ASC, s.schoolYear DESC, s.courseYear ASC, s.name ASC”
        return entityManager.createNamedQuery("getAllSubjects", Subject.class).getResultList();
    }

    //find
    public Subject find(long code){
        var subject = entityManager.find(Subject.class, code);
        if(subject==null){
            throw new IllegalArgumentException("Subject "+code+" not found");
        }
        return subject;
    }

    //enroll student in subject
    public void enrollStudentInSubject(String username, long subjectCode){
        //check if student with username exists using find method from StudentBean
        Student student = entityManager.find(Student.class, username);
        if (student == null) {
            throw new IllegalArgumentException("Student " + username + " not found");
        }
        //check if subject with code exists using find method from this class
        Subject subject = find(subjectCode);

        //add subject and student to list
        student.addSubject(subject);
        subject.addStudent(student);

        //create relationship
        entityManager.merge(student);
        entityManager.merge(subject);

        //persist changes
        entityManager.flush();

        //check if student is now in subject
        if(!subject.getStudents().contains(student)){
            throw new IllegalArgumentException("Student "+username+" not enrolled in subject "+subjectCode);
        }

        //check if subject is now in student
        if(!student.getSubjects().contains(subject)){
            throw new IllegalArgumentException("Subject "+subjectCode+" not enrolled in student "+username);
        }
    }

    //find subject taught in course
    public List<Subject> findSubjectsTaughtInCourse(long courseCode){
        // remember, maps to: “SELECT s FROM Subject s JOIN s.course c WHERE c.code = :courseCode ORDER BY c.name ASC, s.schoolYear DESC, s.courseYear ASC, s.name ASC”
        return entityManager.createNamedQuery("getSubjectsTaughtInCourse", Subject.class)
                .setParameter("courseCode", courseCode)
                .getResultList();
    }
}
