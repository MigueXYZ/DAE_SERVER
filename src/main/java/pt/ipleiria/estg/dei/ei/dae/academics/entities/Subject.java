package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "subjects",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"code","course_code","school_year"})
        }
)
@NamedQueries({
        @NamedQuery(
                name = "getAllSubjects",
                query = "SELECT s FROM Subject s " +
                        "JOIN s.course c " +  // Faz o JOIN para poder aceder ao nome do curso
                        "ORDER BY c.name ASC, s.schoolYear DESC, s.courseYear ASC, s.name ASC"
        ),
        @NamedQuery(
                name = "getSubjectsByCourse",
                query = "SELECT s FROM Subject s " +
                        "JOIN s.course c " +  // Faz o JOIN para poder aceder ao nome do curso
                        "WHERE c.code = :courseCode " +
                        "ORDER BY s.schoolYear DESC, s.courseYear ASC, s.name ASC"
        ),
        @NamedQuery(
                name = "getSubjectsByStudent",
                query = "SELECT s FROM Subject s " +
                        "JOIN s.students st " +  // Faz o JOIN para poder aceder aos estudantes
                        "WHERE st.username = :username " +
                        "ORDER BY s.schoolYear DESC, s.courseYear ASC, s.name ASC"
        ),
        @NamedQuery(
                name = "getSubjectsByTeacher",
                query = "SELECT s FROM Subject s " +
                        "JOIN s.teachers t " +  // Faz o JOIN para poder aceder aos professores
                        "WHERE t.username = :username " +
                        "ORDER BY s.schoolYear DESC, s.courseYear ASC, s.name ASC"
        )
})
public class Subject implements Serializable {

    @Id
    @Column(name = "code")
    private long code;
    @Column(name = "name")
    private String name;
    @Column(name = "school_year")
    private String schoolYear;
    @Column(name = "course_year")
    private int courseYear;
    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "code")
    private Course course;
    @ManyToMany
    @JoinTable(
            name = "subject_student",
            joinColumns = @JoinColumn(
                    name = "subject_code",
                    referencedColumnName = "code"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_username",
                    referencedColumnName = "username"
            )
    )
    private List<Student> students;

    @ManyToMany
    @JoinTable(
            name = "subject_teacher",
            joinColumns = @JoinColumn(
                    name = "subject_code",
                    referencedColumnName = "code"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "teacher_username",
                    referencedColumnName = "username"
            )
    )
    private List<Teacher> teachers;

    public Subject() {
        //create a new list of students
        students = new ArrayList<>();
        teachers = new ArrayList<>();
    }

    public Subject(long code, String name, String schoolYear, int courseYear, Course course) {
        this.code = code;
        this.name = name;
        this.schoolYear = schoolYear;
        this.courseYear = courseYear;
        this.course = course;
        students = new ArrayList<>();
        teachers = new ArrayList<>();
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

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
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

    public boolean isEnrolled(Student student) {
        return students.contains(student);
    }

    public void clearStudents() {
        students.clear();
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public boolean isAssigned(Teacher teacher) {
        return teachers.contains(teacher);
    }

    public void clearTeachers() {
        teachers.clear();
    }

    @Override
    public String toString() {
        return "Subject{" + "code=" + code + ", name=" + name + ", schoolYear=" + schoolYear + ", courseYear=" + courseYear + '}';
    }
}
