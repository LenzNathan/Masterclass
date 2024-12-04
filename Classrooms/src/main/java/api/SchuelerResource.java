package api;

//import de.example.model.Schueler;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/schueler")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SchuelerResource {

    @GET
    public List<Schueler> getAllSchueler() {
        return Schueler.listAll();
    }

    @POST
    public Response createSchueler(Schueler schueler) {
        schueler.persist();
        return Response.status(Response.Status.CREATED).entity(schueler).build();
    }

    @GET
    @Path("/{id}")
    public Schueler getSchueler(@PathParam("id") Integer id) {
        return Schueler.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateSchueler(@PathParam("id") Integer id, Schueler schueler) {
        Schueler existingSchueler = Schueler.findById(id);
        if (existingSchueler == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingSchueler.schuelerVorname = schueler.schuelerVorname;
        existingSchueler.schuelerNachname = schueler.schuelerNachname;
        existingSchueler.schulklasse = schueler.schulklasse;
        existingSchueler.persist();
        return Response.ok(existingSchueler).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSchueler(@PathParam("id") Integer id) {
        Schueler existingSchueler = Schueler.findById(id);
        if (existingSchueler == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingSchueler.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
