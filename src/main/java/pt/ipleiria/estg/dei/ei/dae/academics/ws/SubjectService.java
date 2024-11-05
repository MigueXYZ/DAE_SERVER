package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.StudentDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.SubjectDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.SubjectBean;

@Path("subject")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SubjectService {
    @EJB
    private SubjectBean subjectBean;

    //get all students enrolled in a subject
    @GET
    @Path("{code}/students")
    public Response getStudentsEnrolledInSubject(@PathParam("code") long code) {
        return Response.ok(StudentDTO.from(subjectBean.find(code).getStudents())).build();
    }

    //get all subjects
    @GET
    @Path("/")
    public Response getAllSubjects() {
        return Response.ok(SubjectDTO.from(subjectBean.findAll())).build();
    }

    //get subjects that are taught in a course
    @GET
    @Path("course/{code}")
    public Response getSubjectsByCourse(@PathParam("code") long code) {
        return Response.ok(SubjectDTO.from(subjectBean.findSubjectsTaughtInCourse(code))).build();
    }

    //get subject by code
    @GET
    @Path("{code}")
    public Response getSubject(@PathParam("code") long code) {
        return Response.ok(SubjectDTO.from(subjectBean.find(code))).build();
    }



}
