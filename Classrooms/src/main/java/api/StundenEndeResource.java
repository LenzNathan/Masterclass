package api;

//import de.example.model.StundenEnde;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/stunden-ende")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StundenEndeResource {

    @GET
    public List<StundenEnde> getAllStundenEnde() {
        return StundenEnde.listAll();
    }

    @POST
    public Response createStundenEnde(StundenEnde stundenEnde) {
        stundenEnde.persist();
        return Response.status(Response.Status.CREATED).entity(stundenEnde).build();
    }

    @GET
    @Path("/{id}")
    public StundenEnde getStundenEnde(@PathParam("id") Integer id) {
        return StundenEnde.findById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStundenEnde(@PathParam("id") Integer id) {
        StundenEnde existingStundenEnde = StundenEnde.findById(id);
        if (existingStundenEnde == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingStundenEnde.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
