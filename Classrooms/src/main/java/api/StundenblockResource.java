package api;

//import de.example.model.Stundenblock;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/stundenbloecke")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StundenblockResource {

    @GET
    public List<Stundenblock> getAllStundenbloecke() {
        return Stundenblock.listAll();
    }

    @POST
    public Response createStundenblock(Stundenblock stundenblock) {
        stundenblock.persist();
        return Response.status(Response.Status.CREATED).entity(stundenblock).build();
    }

    @GET
    @Path("/{id}")
    public Stundenblock getStundenblock(@PathParam("id") Integer id) {
        return Stundenblock.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateStundenblock(@PathParam("id") Integer id, Stundenblock stundenblock) {
        Stundenblock existingStundenblock = Stundenblock.findById(id);
        if (existingStundenblock == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingStundenblock.raum = stundenblock.raum;
        existingStundenblock.gruppe = stundenblock.gruppe;
        existingStundenblock.startZeit = stundenblock.startZeit;
        existingStundenblock.endeZeit = stundenblock.endeZeit;
        existingStundenblock.stundenblockDatum = stundenblock.stundenblockDatum;
        existingStundenblock.persist();
        return Response.ok(existingStundenblock).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStundenblock(@PathParam("id") Integer id) {
        Stundenblock existingStundenblock = Stundenblock.findById(id);
        if (existingStundenblock == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingStundenblock.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
