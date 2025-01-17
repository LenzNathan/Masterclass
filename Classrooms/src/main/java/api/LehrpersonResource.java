package api;

//import de.example.model.Lehrperson;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/lehrpersonen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LehrpersonResource {

    @GET
    public List<Lehrperson> getAllLehrpersonen() {
        return Lehrperson.listAll();
    }

    @POST
    public Response createLehrperson(Lehrperson lehrperson) {
        lehrperson.persist();
        return Response.status(Response.Status.CREATED).entity(lehrperson).build();
    }

    @GET
    @Path("/{id}")
    public Lehrperson getLehrperson(@PathParam("id") Integer id) {
        return Lehrperson.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateLehrperson(@PathParam("id") Integer id, Lehrperson lehrperson) {
        Lehrperson existingLehrperson = Lehrperson.findById(id);
        if (existingLehrperson == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingLehrperson.lehrerVorname = lehrperson.lehrerVorname;
        existingLehrperson.lehrerNachname = lehrperson.lehrerNachname;
        existingLehrperson.persist();
        return Response.ok(existingLehrperson).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLehrperson(@PathParam("id") Integer id) {
        Lehrperson existingLehrperson = Lehrperson.findById(id);
        if (existingLehrperson == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingLehrperson.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

