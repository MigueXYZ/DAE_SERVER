package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import jakarta.validation.constraints.NotBlank;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDTO implements Serializable {
    private long code;
    private String name;

    public CourseDTO() {
    }

    public CourseDTO(long code, String name) {
        this.code = code;
        this.name = name;
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

    @Override
    public String toString() {
        return "CourseDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }

    // Método estático que converte uma entidade Course numa DTO
    public static CourseDTO from(Course course) {
        return new CourseDTO(course.getCode(), course.getName());
    }

    // Método para converter uma lista de Course para uma lista de CourseDTO
    public static List<CourseDTO> from(List<Course> courses) {
        return courses.stream().map(CourseDTO::from).collect(Collectors.toList());
    }

}
