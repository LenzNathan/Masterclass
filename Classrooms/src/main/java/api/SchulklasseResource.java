package api;

//import de.example.model.Schulklasse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/*
@Path("/schulklassen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SchulklasseResource {

    @GET
    public List<Schulklasse> getAllSchulklassen() {
        return Schulklasse.listAll();
    }

    @POST
    public Response createSchulklasse(Schulklasse schulklasse) {
        schulklasse.persist();
        return Response.status(Response.Status.CREATED).entity(schulklasse).build();
    }

    @GET
    @Path("/{id}")
    public Schulklasse getSchulklasse(@PathParam("id") Integer id) {
        return Schulklasse.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateSchulklasse(@PathParam("id") Integer id, Schulklasse schulklasse) {
        Schulklasse existingSchulklasse = Schulklasse.findById(id);
        if (existingSchulklasse == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingSchulklasse.klassenId = schulklasse.klassenId;
        existingSchulklasse.jahrgang = schulklasse.jahrgang;
        existingSchulklasse.abteilung = schulklasse.abteilung;
        existingSchulklasse.persist();
        return Response.ok(existingSchulklasse).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSchulklasse(@PathParam("id") Integer id) {
        Schulklasse existingSchulklasse = Schulklasse.findById(id);
        if (existingSchulklasse == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingSchulklasse.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

*/