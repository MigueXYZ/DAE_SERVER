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

    @PostConstruct
    public void populateDB() {
        // Populate the database with some initial data
        System.out.println("Populating the database with some initial data");

        //Students
        studentBean.create("student1", "password1", "Student 1", "student1@ipleiria.pt");
        studentBean.create("student2", "password2", "Student 2", "student2@ipleiria.pt");
        studentBean.create("student3", "password3", "Student 3", "student3@ipleiria.pt");
    }
}
