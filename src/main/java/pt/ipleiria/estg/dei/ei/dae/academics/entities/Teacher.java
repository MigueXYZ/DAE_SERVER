package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher extends User{

    private String office ;

    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;

    public Teacher() {
        subjects = new ArrayList<>();
    }

    public Teacher(String username, String password, String name, String email, String office) {
        super(username, password, name, email);
        this.office = office;
        subjects = new ArrayList<>();
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    //get Subjects
    public List<Subject> getSubjects() {
        return subjects;
    }

    //set Subjects
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    //add Subject

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    //remove Subject
    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    //toString
    @Override
    public String toString() {
        return "Teacher{" +
                "office='" + office + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
