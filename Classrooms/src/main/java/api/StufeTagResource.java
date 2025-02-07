package api;

//import de.example.model.StufeTag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/*
@Path("/stufetags")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StufeTagResource {

    @GET
    public List<StufeTag> getAllStufeTags() {
        return StufeTag.listAll();
    }

    @POST
    public Response createStufeTag(StufeTag stufeTag) {
        stufeTag.persist();
        return Response.status(Response.Status.CREATED).entity(stufeTag).build();
    }

    @GET
    @Path("/{stufeId}/{tagId}")
    public StufeTag getStufeTag(@PathParam("stufeId") Integer stufeId, @PathParam("tagId") Integer tagId) {
        return StufeTag.find("stufeId = ?1 and tagId = ?2", stufeId, tagId).firstResult();
    }

    @DELETE
    @Path("/{stufeId}/{tagId}")
    public Response deleteStufeTag(@PathParam("stufeId") Integer stufeId, @PathParam("tagId") Integer tagId) {
        StufeTag stufeTag = StufeTag.find("stufeId = ?1 and tagId = ?2", stufeId, tagId).firstResult();
        if (stufeTag == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        stufeTag.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
*/