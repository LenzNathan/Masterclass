package api;

import jpa.Student;
import jakarta.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    @GET
    public List<Student> getAllStudent() {
        return Student.listAll();
    }

    @POST
    @Transactional
    public Response createStudent(Student student) {
        student.persist();
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @GET
    @Path("/{id}")
    public Student getStudent(@PathParam("id") Integer id) {
        return Student.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateStudent(@PathParam("id") Integer id, Student student) {
        Student existingStudent = Student.findById(id);
        if (existingStudent == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        boolean changed = false;

        // Update 'firstname' if it's different from the existing one
        if (existingStudent.getFirstname() != null && !existingStudent.getFirstname().equals(student.getFirstname())) {
            existingStudent.setFirstname(student.getFirstname());
            changed = true;
        }

        // Update 'lastname' if it's different from the existing one
        if (existingStudent.getLastname() != null && !existingStudent.getLastname().equals(student.getLastname())) {
            existingStudent.setLastname(student.getLastname());
            changed = true;
        }

        // Update 'schulklasse' if it's different from the existing one
        if (existingStudent.getSchulklasse() != null && !existingStudent.getSchulklasse().equals(student.getSchulklasse())) {
            existingStudent.setSchulklasse(student.getSchulklasse());
            changed = true;
        }

        // Update 'group' if it's different from the existing one
        if (existingStudent.getGroup() != null && !existingStudent.getGroup().equals(student.getGroup())) {
            existingStudent.setGroup(student.getGroup());
            changed = true;
        }

        // Persist changes only if something has changed
        if (changed) {
            existingStudent.persist();
            return Response.status(Response.Status.OK).entity(existingStudent).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteStudent(@PathParam("id") Integer id) {
        Student existingStudent = Student.findById(id);
        if (existingStudent == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingStudent.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}