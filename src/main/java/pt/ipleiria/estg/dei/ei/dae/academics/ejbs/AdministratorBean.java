package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Administrator;

import java.util.List;

@Stateless
public class AdministratorBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(String username, String password, String name, String email) {
        entityManager.persist(new Administrator(username, password, name, email));
    }

    public Administrator find(String username){
        var administrator = entityManager.find(Administrator.class, username);
        if(administrator==null){
            throw new IllegalArgumentException("Administrator "+username+" not found");
        }
        return administrator;
    }

    //find all
    public List<Administrator> findAll() {
        return entityManager.createNamedQuery("getAllAdmins", Administrator.class).getResultList();
    }

}
