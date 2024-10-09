package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import java.util.List;

@Stateless
public class SubjectBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(long code, String name,String schoolYear, int courseYear, Course course) {
        Subject subject = new Subject(code, name, schoolYear, courseYear, course);
        entityManager.persist(subject);
    }

    //find
    public Subject find(long code){
        var subject = entityManager.find(Subject.class, code);
        if(subject==null){
            throw new IllegalArgumentException("Subject "+code+" not found");
        }
        return subject;
    }

    //findAll
    public List<Subject> findAll() {
        // remember, maps to: “SELECT s FROM Subject s ORDER BY s.courseYear, s.schoolYear”
        return entityManager.createNamedQuery("getAllSubjects", Subject.class).getResultList();
    }
}
