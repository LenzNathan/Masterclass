package api;

//import de.example.model.Gebaeude;

import jpa.Gebaeude;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/*
@Path("/gebaeude")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GebaeudeResource {

    @GET
    public List<Gebaeude> getAllGebaeude() {
        return Gebaeude.listAll();
    }

    @POST
    public Response createGebaeude(Gebaeude gebaeude) {
        gebaeude.persist();
        return Response.status(Response.Status.CREATED).entity(gebaeude).build();
    }

    @GET
    @Path("/{id}")
    public Gebaeude getGebaeude(@PathParam("id") Integer id) {
        return Gebaeude.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateGebaeude(@PathParam("id") Integer id, Gebaeude gebaeude) {
        Gebaeude existingGebaeude = Gebaeude.findById(id);
        if (existingGebaeude == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingGebaeude.gebKuerzel = gebaeude.gebKuerzel;
        existingGebaeude.gebName = gebaeude.gebName;
        existingGebaeude.persist();
        return Response.ok(existingGebaeude).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGebaeude(@PathParam("id") Integer id) {
        Gebaeude existingGebaeude = Gebaeude.findById(id);
        if (existingGebaeude == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingGebaeude.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
*/