package api;

import jpa.Abteilung;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/abteilungen")
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
        Abteilung existingAbteilung = Abteilung.findById(id);
        if (existingAbteilung == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingAbteilung.nickname = abteilung.nickname;
        existingAbteilung.name = abteilung.name;
        existingAbteilung.persist();
        return Response.ok(existingAbteilung).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteAbteilung(@PathParam("id") Integer id) {
        Abteilung existingAbteilung = Abteilung.findById(id);
        if (existingAbteilung == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingAbteilung.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}