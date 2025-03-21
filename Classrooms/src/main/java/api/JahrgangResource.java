package api;

import jakarta.transaction.Transactional;
import jpa.Jahrgang;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/jahrgang")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JahrgangResource {

    @GET
    public List<Jahrgang> getAllJahrgangn() {
        return Jahrgang.listAll();
    }

    @POST
    @Transactional
    public Response createJahrgang(Jahrgang abteilungsStufe) {
        abteilungsStufe.persist();
        return Response.status(Response.Status.CREATED).entity(abteilungsStufe).build();
    }

    @GET
    @Path("/{id}")
    public Jahrgang getJahrgang(@PathParam("id") Integer id) {
        return Jahrgang.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateJahrgang(@PathParam("id") Integer id, Jahrgang abteilungsStufe) {
        Jahrgang existingStufe = Jahrgang.findById(id);
        if (existingStufe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingStufe.setStufe(abteilungsStufe.getStufe());
        existingStufe.setAbteilung(abteilungsStufe.getAbteilung());
        existingStufe.persist();
        return Response.ok(existingStufe).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteJahrgang(@PathParam("id") Integer id) {
        Jahrgang existingStufe = Jahrgang.findById(id);
        if (existingStufe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingStufe.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}