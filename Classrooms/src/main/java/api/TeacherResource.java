package api;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jpa.Teacher;

import java.util.List;

@Path("/teachers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeacherResource {

    @GET
    public List<Teacher> getAllTeacher() {
        return Teacher.listAll();
    }

    @POST
    @Transactional
    public Response createTeacher(Teacher teacher) {
        teacher.persist();
        return Response.status(Response.Status.CREATED).entity(teacher).build();
    }

    @GET
    @Path("/{id}")
    public Teacher getTeacher(@PathParam("id") Integer id) {
        return Teacher.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateTeacher(@PathParam("id") Integer id, Teacher teacher) {
        boolean changed = false;
        Teacher existingTeacher = Teacher.findById(id);

        if (existingTeacher == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (teacher.getNickname() != null) {
            existingTeacher.setNickname(teacher.getNickname());
            changed = true;
        }

        if (teacher.getFirstname() != null) {
            existingTeacher.setFirstname(teacher.getFirstname());
            changed = true;
        }

        if (teacher.getLastname() != null) {
            existingTeacher.setLastname(teacher.getLastname());
            changed = true;
        }

        if (teacher.getTags() != null) {
            existingTeacher.setTags(teacher.getTags());
            changed = true;
        }

        if (changed) {
            existingTeacher.persist();
            return Response.ok(existingTeacher).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteTeacher(@PathParam("id") Integer id) {
        Teacher existingTeacher = Teacher.findById(id);
        if (existingTeacher == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingTeacher.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @DELETE
    @Path("/all")
    @Transactional
    public jakarta.ws.rs.core.Response deleteTeachers() {
        List<Teacher> teachers = Teacher.listAll();
        if (teachers.isEmpty()) {
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.NOT_FOUND).build();
        } else {
            for (Teacher teacher : teachers) {
                teacher.delete();
            }
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.OK).build();
        }
    }
}