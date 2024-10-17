package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;

import java.util.List;

@Stateless
public class CourseBean {

    @PersistenceContext
    private EntityManager entityManager;

    //create,findAll
    public void create(long code, String name) {
        Course course = new Course(code, name);
        entityManager.persist(course);
    }

    public List<Course> findAll() {
        // remember, maps to: “SELECT c FROM Course c ORDER BY c.name”
        return entityManager.createNamedQuery("getAllCourses", Course.class).getResultList();
    }

    public Course find(long code){
        var course = entityManager.find(Course.class, code);
        if(course==null){
            throw new IllegalArgumentException("Course "+code+" not found");
        }
        return course;
    }



}
