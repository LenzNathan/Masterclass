package api;

import jpa.Jahrgang;
import jakarta.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/jahrgang")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JahrgangResource {

    @GET
    public List<Jahrgang> getAllJahrgang() {
        return Jahrgang.listAll();
    }

    @POST
    @Transactional
    public Response createJahrgang(Jahrgang jahrgang) {
        jahrgang.persist();
        return Response.status(Response.Status.CREATED).entity(jahrgang).build();
    }

    @GET
    @Path("/{id}")
    public Response getJahrgang(@PathParam("id") Integer id) {
        Jahrgang a = Jahrgang.findById(id);
        if (a == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else {
            return Response.status(Response.Status.OK).entity(a).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateJahrgang(@PathParam("id") Integer id, Jahrgang abteilungsStufe) {
        boolean changed = false;
        Jahrgang existingJahrgang = Jahrgang.findById(id);
        if (existingJahrgang == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (existingJahrgang.getStufe() != 0) {
            existingJahrgang.setStufe(abteilungsStufe.getStufe());
            changed = true;
        }

        if (existingJahrgang.getAbteilung() != null) {
            existingJahrgang.setAbteilung(abteilungsStufe.getAbteilung());
            changed = true;
        }

        if (changed) {
            existingJahrgang.persist();
            return Response.status(Response.Status.OK).entity(existingJahrgang).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteJahrgang(@PathParam("id") Integer id) {
        Jahrgang existingStufe = Jahrgang.findById(id);
        if (existingStufe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingStufe.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}