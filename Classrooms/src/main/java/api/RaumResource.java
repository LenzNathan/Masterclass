package api;

//import de.example.model.Raum;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/*
@Path("/raeume")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RaumResource {

    @GET
    public List<Raum> getAllRaeume() {
        return Raum.listAll();
    }

    @POST
    public Response createRaum(Raum raum) {
        raum.persist();
        return Response.status(Response.Status.CREATED).entity(raum).build();
    }

    @GET
    @Path("/{id}")
    public Raum getRaum(@PathParam("id") Integer id) {
        return Raum.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateRaum(@PathParam("id") Integer id, Raum raum) {
        Raum existingRaum = Raum.findById(id);
        if (existingRaum == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingRaum.raumNummer = raum.raumNummer;
        existingRaum.raumKapazitaet = raum.raumKapazitaet;
        existingRaum.gebaeude = raum.gebaeude;
        existingRaum.persist();
        return Response.ok(existingRaum).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRaum(@PathParam("id") Integer id) {
        Raum existingRaum = Raum.findById(id);
        if (existingRaum == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingRaum.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

*/