package api;

import jpa.LessonBegin;
import jakarta.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/lesson-starts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LessonBeginResource {

    @GET
    public List<LessonBegin> getAllLessonBegin() {
        return LessonBegin.listAll();
    }

    @POST
    @Transactional
    public Response createLessonBegin(LessonBegin lessonBegin) {
        lessonBegin.persist();
        return Response.status(Response.Status.CREATED).entity(lessonBegin).build();
    }

    @GET
    @Path("/{id}")
    public Response getLessonBegin(@PathParam("id") Integer id) {
        LessonBegin lessonBegin = LessonBegin.findById(id);
        if (lessonBegin == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lessonBegin).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateLessonBegin(@PathParam("id") Integer id, LessonBegin lessonBegin) {
        boolean changed = false;
        LessonBegin existingLessonBegin = LessonBegin.findById(id);
        if (existingLessonBegin == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (lessonBegin.getLessonNumber() != 0) {
            existingLessonBegin.setLessonNumber(lessonBegin.getLessonNumber());
            changed = true;
        }
        if (lessonBegin.getTime() != null) {
            existingLessonBegin.setTime(lessonBegin.getTime());
            changed = true;
        }

        if (changed) {
            existingLessonBegin.persist();
            return Response.ok(existingLessonBegin).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteLessonBegin(@PathParam("id") Integer id) {
        LessonBegin existingLessonBegin = LessonBegin.findById(id);
        if (existingLessonBegin == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingLessonBegin.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
