package api;

//import de.example.model.LehrerTag;
//import de.example.model.Tag;
//import de.example.model.Lehrperson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/lehrertags")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LehrerTagResource {

    @GET
    public List<LehrerTag> getAllLehrerTags() {
        return LehrerTag.listAll();
    }

    @POST
    public Response createLehrerTag(LehrerTag lehrerTag) {
        lehrerTag.persist();
        return Response.status(Response.Status.CREATED).entity(lehrerTag).build();
    }

    @GET
    @Path("/{lehrerId}/{tagId}")
    public LehrerTag getLehrerTag(@PathParam("lehrerId") Integer lehrerId, @PathParam("tagId") Integer tagId) {
        return LehrerTag.find("lehrerId = ?1 and tagId = ?2", lehrerId, tagId).firstResult();
    }

    @DELETE
    @Path("/{lehrerId}/{tagId}")
    public Response deleteLehrerTag(@PathParam("lehrerId") Integer lehrerId, @PathParam("tagId") Integer tagId) {
        LehrerTag lehrerTag = LehrerTag.find("lehrerId = ?1 and tagId = ?2", lehrerId, tagId).firstResult();
        if (lehrerTag == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        lehrerTag.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
