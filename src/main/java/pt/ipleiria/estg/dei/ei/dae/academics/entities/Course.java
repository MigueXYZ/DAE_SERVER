package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses", uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
@NamedQueries({
    @NamedQuery(
            name = "getAllCourses",
            query = "SELECT c FROM Course c ORDER BY c.name" // JPQL
    )
})

public class Course implements Serializable {
    @Id
    private long code;
    private String name;
    @OneToMany(mappedBy = "course")
    private List<Student> students;

    public Course() {
        students = new ArrayList<>();
    }

    public Course(long code, String name) {
        this.code = code;
        this.name = name;
        students = new ArrayList<>();
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void clearStudents() {
        students.clear();
    }

    @Override
    public String toString() {
        return "Course{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }

}
