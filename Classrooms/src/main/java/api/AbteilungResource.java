package api;

import jpa.Abteilung;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/abteilung")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AbteilungResource {

    @GET
    public List<Abteilung> getAllAbteilungen() {
        return Abteilung.listAll();
    }

    @POST
    @Transactional
    public Response createAbteilung(Abteilung abteilung) {
        abteilung.persist();
        return Response.status(Response.Status.CREATED).entity(abteilung).build();
    }

    @GET
    @Path("/{id}")
    public Abteilung getAbteilung(@PathParam("id") Integer id) {
        return Abteilung.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateAbteilung(@PathParam("id") Integer id, Abteilung abteilung) {
        boolean changed = false;
        Abteilung existingAbteilung = Abteilung.findById(id);
        if (existingAbteilung == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (abteilung.getNickname() != null) {
            existingAbteilung.setNickname(abteilung.getNickname());
            changed = true;
        }
        if (abteilung.getName() != null) {
            existingAbteilung.setName(abteilung.getName());
            changed = true;
        }
        if(changed) {
            existingAbteilung.persist();
            return Response.ok(existingAbteilung).build();
        }else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }
}