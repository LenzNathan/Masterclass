package api;

//import de.example.model.Gruppe;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/gruppen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GruppeResource {

    @GET
    public List<Gruppe> getAllGruppen() {
        return Gruppe.listAll();
    }

    @POST
    public Response createGruppe(Gruppe gruppe) {
        gruppe.persist();
        return Response.status(Response.Status.CREATED).entity(gruppe).build();
    }

    @GET
    @Path("/{id}")
    public Gruppe getGruppe(@PathParam("id") Integer id) {
        return Gruppe.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateGruppe(@PathParam("id") Integer id, Gruppe gruppe) {
        Gruppe existingGruppe = Gruppe.findById(id);
        if (existingGruppe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingGruppe.gruppeName = gruppe.gruppeName;
        existingGruppe.schulklasse = gruppe.schulklasse;
        existingGruppe.persist();
        return Response.ok(existingGruppe).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGruppe(@PathParam("id") Integer id) {
        Gruppe existingGruppe = Gruppe.findById(id);
        if (existingGruppe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingGruppe.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

