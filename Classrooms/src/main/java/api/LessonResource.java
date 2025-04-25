package api;

import jakarta.transaction.Transactional;
import jpa.Lesson;
import jpa.LessonEnd;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/lessons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LessonResource {

    @GET
    public List<Lesson> getAllLessons() {
        return Lesson.listAll();
    }

    @POST
    @Transactional
    public Response createLesson(Lesson lesson) {
        lesson.persist();
        return Response.status(Response.Status.CREATED).entity(lesson).build();
    }

    @GET
    @Path("/{id}")
    public Response getLesson(@PathParam("id") Integer id) {
        Lesson lesson = Lesson.findById(id);
        if (lesson == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lesson).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateLesson(@PathParam("id") Integer id, Lesson lesson) {
        boolean changed = false;
        Lesson existingLesson = Lesson.findById(id);
        if (existingLesson == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (lesson.getDate() != null) {
            existingLesson.setDate(lesson.getDate());
            changed = true;
        }
        if (lesson.getSubject() != null) {
            existingLesson.setSubject(lesson.getSubject());
            changed = true;
        }
        if (lesson.getRoom() != null) {
            existingLesson.setRoom(lesson.getRoom());
            changed = true;
        }
        if (lesson.getGroups() != null) {
            existingLesson.setGroups(lesson.getGroups());
            changed = true;
        }
        if (lesson.getBegin() != null) {
            existingLesson.setBegin(lesson.getBegin());
            changed = true;
        }
        if (lesson.getEnd() != null) {
            existingLesson.setEnd(lesson.getEnd());
            changed = true;
        }

        if (changed) {
            existingLesson.persist();
            return Response.ok(existingLesson).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteLesson(@PathParam("id") Integer id) {
        Lesson existingLesson = Lesson.findById(id);
        if (existingLesson == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingLesson.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @DELETE
    @Path("/all")
    @Transactional
    public jakarta.ws.rs.core.Response deleteAbteilung() {
        List<Lesson> lessons = Lesson.listAll();
        if (lessons.isEmpty()){
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.NOT_FOUND).build();
        }else {
            for (Lesson lesson : lessons) {
                lesson.delete();
            }
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.OK).build();
        }
    }
}