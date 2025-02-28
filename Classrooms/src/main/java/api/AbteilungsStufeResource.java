package api;

//import de.example.model.AbteilungsStufe;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/*
@Path("/abteilungsstufen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AbteilungsStufeResource {

    @GET
    public List<AbteilungsStufe> getAllAbteilungsStufen() {
        return AbteilungsStufe.listAll();
    }

    @POST
    public Response createAbteilungsStufe(AbteilungsStufe abteilungsStufe) {
        abteilungsStufe.persist();
        return Response.status(Response.Status.CREATED).entity(abteilungsStufe).build();
    }

    @GET
    @Path("/{id}")
    public AbteilungsStufe getAbteilungsStufe(@PathParam("id") Integer id) {
        return AbteilungsStufe.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateAbteilungsStufe(@PathParam("id") Integer id, AbteilungsStufe abteilungsStufe) {
        AbteilungsStufe existingStufe = AbteilungsStufe.findById(id);
        if (existingStufe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingStufe.stufe = abteilungsStufe.stufe;
        existingStufe.abteilung = abteilungsStufe.abteilung;
        existingStufe.persist();
        return Response.ok(existingStufe).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAbteilungsStufe(@PathParam("id") Integer id) {
        AbteilungsStufe existingStufe = AbteilungsStufe.findById(id);
        if (existingStufe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingStufe.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

*/