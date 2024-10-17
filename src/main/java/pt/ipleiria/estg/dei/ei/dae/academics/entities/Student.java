package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
@NamedQueries({
        @NamedQuery(
                name = "getAllStudents",
                query = "SELECT s FROM Student s ORDER BY s.name" // JPQL
        )
})
public class Student implements Serializable {

    @Id
    @Column(name = "username")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "email")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotNull
    private Course course;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private List<Subject> subjects;

    public Student() {
        subjects = new ArrayList<>();
    }

    public Student(String username, String password, String name, String email, Course course) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.course = course;
        subjects = new ArrayList<>();
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Course getCourse(){
        return course;
    }

    public void setCourse(Course course){
        this.course = course;
    }

    public List<Subject> getSubjects(){
        return subjects;
    }

    public void setSubjects(List<Subject> subjects){
        this.subjects = subjects;
    }

    public void addSubject(Subject subject){
        subjects.add(subject);
    }

    public void removeSubject(Subject subject){
        subjects.remove(subject);
    }

    public void clearSubjects(){
        subjects.clear();
    }

    @Override
    public String toString(){
        return "Student{" + "username=" + username + ", name=" + name + ", email=" + email + '}';
    }

}
