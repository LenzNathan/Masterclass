package api;

//import de.example.model.RaumTag;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/*
@Path("/raum-tags")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RaumTagResource {

    @GET
    public List<RaumTag> getAllRaumTags() {
        return RaumTag.listAll();
    }

    @POST
    public Response createRaumTag(RaumTag raumTag) {
        raumTag.persist();
        return Response.status(Response.Status.CREATED).entity(raumTag).build();
    }

    @GET
    @Path("/{raumId}/{tagId}")
    public RaumTag getRaumTag(@PathParam("raumId") Integer raumId, @PathParam("tagId") Integer tagId) {
        return RaumTag.findById(raumId, tagId);
    }

    @DELETE
    @Path("/{raumId}/{tagId}")
    public Response deleteRaumTag(@PathParam("raumId") Integer raumId, @PathParam("tagId") Integer tagId) {
        RaumTag existingRaumTag = RaumTag.findById(raumId, tagId);
        if (existingRaumTag == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingRaumTag.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
*/