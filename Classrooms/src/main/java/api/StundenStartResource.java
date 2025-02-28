package api;

//import de.example.model.StundenStart;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/*
@Path("/stunden-start")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StundenStartResource {

    @GET
    public List<StundenStart> getAllStundenStart() {
        return StundenStart.listAll();
    }

    @POST
    public Response createStundenStart(StundenStart stundenStart) {
        stundenStart.persist();
        return Response.status(Response.Status.CREATED).entity(stundenStart).build();
    }

    @GET
    @Path("/{id}")
    public StundenStart getStundenStart(@PathParam("id") Integer id) {
        return StundenStart.findById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStundenStart(@PathParam("id") Integer id) {
        StundenStart existingStundenStart = StundenStart.findById(id);
        if (existingStundenStart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingStundenStart.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
*/