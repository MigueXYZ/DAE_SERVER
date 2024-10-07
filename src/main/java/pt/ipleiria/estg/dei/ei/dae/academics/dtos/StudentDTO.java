package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDTO  implements Serializable {
    private String username;
    private String password;
    private String name;
    private String email;
    private String courseName;
    private long courseCode;

    public StudentDTO() {
    }

    public StudentDTO(String username, String password, String name, String email, String courseName, long courseCode) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(long courseCode) {
        this.courseCode = courseCode;
    }

    // Converts an entity Student to a DTO Student class
    public static StudentDTO from(Student student) {
        return new StudentDTO(
                student.getUsername(),
                student.getPassword(),
                student.getName(),
                student.getEmail(),
                student.getCourse().getName(),
                student.getCourse().getCode()
        );
    }
    // converts an entire list of entities into a list of DTOs
    public static List<StudentDTO> from(List<Student> students) {
        return students.stream().map(StudentDTO::from).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                '}';
    }
}
