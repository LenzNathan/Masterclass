package api;

import jpa.LessonEnd;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/lesson-ends")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LessonEndResource {

    @GET
    public List<LessonEnd> getAllLessonEnd() {
        return LessonEnd.listAll();
    }

    @POST
    @Transactional
    public Response createLessonEnd(LessonEnd LessonEnd) {
        LessonEnd.persist();
        return Response.status(Response.Status.CREATED).entity(LessonEnd).build();
    }

    @GET
    @Path("/{id}")
    public Response getLessonEnd(@PathParam("id") Integer id) {
        LessonEnd lessonEnd = LessonEnd.findById(id);
        if (lessonEnd == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lessonEnd).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateLessonEnd(@PathParam("id") Integer id, LessonEnd lessonEnd) {
        boolean changed = false;
        LessonEnd existingLessonEnd = LessonEnd.findById(id);
        if (existingLessonEnd == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (lessonEnd.getLessonNumber() != 0) {
            existingLessonEnd.setLessonNumber(lessonEnd.getLessonNumber());
            changed = true;
        }
        if (lessonEnd.getTime() != null) {
            existingLessonEnd.setTime(lessonEnd.getTime());
            changed = true;
        }

        if (changed) {
            existingLessonEnd.persist();
            return Response.ok(existingLessonEnd).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteLessonEnd(@PathParam("id") Integer id) {
        LessonEnd existingLessonEnd = LessonEnd.findById(id);
        if (existingLessonEnd == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingLessonEnd.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/all")
    @Transactional
    public jakarta.ws.rs.core.Response deleteLessonEnds() {
        List<LessonEnd> lessonEnds = LessonEnd.listAll();
        if (lessonEnds.isEmpty()){
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.NOT_FOUND).build();
        }else {
            for (LessonEnd lessonEnd : lessonEnds) {
                lessonEnd.delete();
            }
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.OK).build();
        }
    }
}
