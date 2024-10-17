package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class ConfigBean {

    @EJB
    private  StudentBean studentBean;
    @EJB
    private  CourseBean courseBean;
    @EJB
    private  SubjectBean subjectBean;

    @PostConstruct
    public void populateDB() {
        System.out.println("Populating the database with some initial data");
        createCourses();
        createSubjects();
        createStudents();
        enrollStudents();
    }

    private void createCourses() {
        courseBean.create(1, "Computer Science");
        courseBean.create(2, "Software Engineering");
        courseBean.create(3, "Information Systems");
    }

    private void createSubjects(){
        subjectBean.create(1, "Mathematics", "1st", 1, 1);
        subjectBean.create(2, "Programming", "1st", 1, 1);
        subjectBean.create(3, "Databases", "1st", 1, 1);
        subjectBean.create(4, "Mathematics", "1st", 1, 2);
        subjectBean.create(5, "Programming", "1st", 1, 2);
        subjectBean.create(6, "Databases", "1st", 1, 2);
        subjectBean.create(7, "Mathematics", "1st", 1, 3);
        subjectBean.create(8, "Programming", "1st", 1, 3);
        subjectBean.create(9, "Databases", "1st", 1, 3);
    }

    private void createStudents() {
        studentBean.create("up201800000", "John Doe", "johndoe", "johndoe@email.pt", 1);
        studentBean.create("up201800001", "Jane Doe", "janedoe", "janedoe@wmail.pt", 1);
        studentBean.create("up201800002", "Alice Doe", "alicedoe", "alicedoe@email.pt", 1);
        studentBean.create("up201800003", "Bob Doe", "bobdoe", "bobdoe@wmail.pt", 1);
    }

    private void enrollStudents(){
        subjectBean.enrollStudentInSubject("up201800000", 1);
        subjectBean.enrollStudentInSubject("up201800000", 2);
        subjectBean.enrollStudentInSubject("up201800000", 3);
        subjectBean.enrollStudentInSubject("up201800001", 1);
        subjectBean.enrollStudentInSubject("up201800001", 2);
        subjectBean.enrollStudentInSubject("up201800001", 3);
        subjectBean.enrollStudentInSubject("up201800002", 1);
        subjectBean.enrollStudentInSubject("up201800002", 2);
        subjectBean.enrollStudentInSubject("up201800002", 3);
        subjectBean.enrollStudentInSubject("up201800003", 1);
        subjectBean.enrollStudentInSubject("up201800003", 2);
        subjectBean.enrollStudentInSubject("up201800003", 3);
    }
}
