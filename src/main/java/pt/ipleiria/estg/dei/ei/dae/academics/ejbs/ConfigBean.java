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

    @PostConstruct
    public void populateDB() {
        // Populate the database with some initial data
        System.out.println("Populating the database with some initial data");

        //Courses
        courseBean.create(1, "Course 1");
        courseBean.create(2, "Course 2");
        courseBean.create(3, "Course 3");

        //Students
        studentBean.create("student1", "password1", "Student 1", "student1@ipleiria.pt",1);
        studentBean.create("student2", "password2", "Student 2", "student2@ipleiria.pt",2);
        studentBean.create("student3", "password3", "Student 3", "student3@ipleiria.pt",3);
    }
}
