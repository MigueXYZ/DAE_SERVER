package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
    //get all subjects ordered by Course year and then by scholarYear
    @NamedQuery(
            name = "getAllSubjects",
            query = "SELECT s FROM Subject s ORDER BY s.courseYear, s.schoolYear" // JPQL
    )
})
public class Subject implements Serializable {
    @Id
    @NotBlank(message = "Code is mandatory")
    private long code;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "School year is mandatory")
    private String schoolYear;
    @NotBlank(message = "Course year is mandatory")
    private int courseYear;
    @NotBlank(message = "Semester is mandatory")
    @ManyToOne
    private Course course;
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;

    public Subject(long code, String name, String schoolYear, int courseYear, Course course) {
        this.code = code;
        this.name = name;
        this.schoolYear = schoolYear;
        this.courseYear = courseYear;
        this.course = course;
        students = new ArrayList<>();
    }

    public Subject() {
        students = new ArrayList<>();
    }

    @NotBlank(message = "Code is mandatory")
    public long getCode() {
        return code;
    }

    public void setCode(@NotBlank(message = "Code is mandatory") long code) {
        this.code = code;
    }

    public @NotBlank(message = "Name is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is mandatory") String name) {
        this.name = name;
    }

    public @NotBlank(message = "School year is mandatory") String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(@NotBlank(message = "School year is mandatory") String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @NotBlank(message = "Course year is mandatory")
    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(@NotBlank(message = "Course year is mandatory") int courseYear) {
        this.courseYear = courseYear;
    }

    public @NotBlank(message = "Semester is mandatory") Course getCourse() {
        return course;
    }

    public void setCourse(@NotBlank(message = "Semester is mandatory") Course course) {
        this.course = course;
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


}
