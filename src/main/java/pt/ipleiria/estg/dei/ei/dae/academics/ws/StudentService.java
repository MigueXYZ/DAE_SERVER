package pt.ipleiria.estg.dei.ei.dae.academics.ws;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.StudentDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.SubjectDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.StudentBean;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.MediaType;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.util.List;

@Path("student") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class StudentService {
    @EJB
    private StudentBean studentBean;
    @GET // means: to call this endpoint, we need to use the HTTP GET method
    @Path("/") // means: the relative url path is “/api/student/”
    public List<StudentDTO> getAllStudents() {
        return StudentDTO.from(studentBean.findAll());
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewStudent(StudentDTO studentDTO) {
        studentBean.create(
                studentDTO.getUsername(),
                studentDTO.getPassword(),
                studentDTO.getName(),
                studentDTO.getEmail(),
                studentDTO.getCourseCode()
        );
        Student newStudent = studentBean.find(studentDTO.getUsername());

        return Response.status(Response.Status.CREATED)
                .entity(StudentDTO.from(newStudent))
                .build();
    }

    @GET
    @Path("{username}")
    public Response getStudent(@PathParam("username") String username) {
        var student = studentBean.find(username);
        return Response.ok(StudentDTO.from(student)).build();
    }

    @GET
    @Path("{username}/subjects")
    public Response getStudentSubjects(@PathParam("username") String username) {
        var student = studentBean.findWithSubjects(username);
        return Response.ok(SubjectDTO.from(student.getSubjects())).build();
    }

    @POST
    @Path("{username}/enroll/{subjectCode}")
    public Response enrollStudentInSubject(@PathParam("username") String username, @PathParam("subjectCode") long subjectCode) {
        studentBean.enrollStudentInSubject(username, subjectCode);
        var student = studentBean.findWithSubjects(username);
        return Response.ok(SubjectDTO.from(student.getSubjects())).build();
    }

    @POST
    @Path("{username}/unenroll/{subjectCode}")
    public Response unenrollStudentInSubject(@PathParam("username") String username, @PathParam("subjectCode") long subjectCode) {
        studentBean.unenrollStudentInSubject(username, subjectCode);
        var student = studentBean.findWithSubjects(username);
        return Response.ok(SubjectDTO.from(student.getSubjects())).build();
    }

    @DELETE
    @Path("{username}")
    public Response deleteStudent(@PathParam("username") String username) {
        studentBean.delete(username);
        return Response.ok().build();
    }

    //update student
    @PATCH
    @Path("{username}")
    public Response updateStudent(@PathParam("username") String username, StudentDTO studentDTO) {
        studentBean.update(username, studentDTO.getPassword(), studentDTO.getName(), studentDTO.getEmail(), studentDTO.getCourseCode());
        var student = studentBean.find(username);
        return Response.ok(StudentDTO.from(student)).build();
    }

    //insert new student
    @POST
    @Path("/")
    public Response insertStudent(StudentDTO studentDTO) {
        studentBean.create(studentDTO.getUsername(), studentDTO.getPassword(), studentDTO.getName(), studentDTO.getEmail(), studentDTO.getCourseCode());
        var student = studentBean.find(studentDTO.getUsername());
        return Response.ok(StudentDTO.from(student)).build();
    }



}