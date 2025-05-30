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
    public List<Abteilung> getAllAbteilung() {
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
    public Response getAbteilung(@PathParam("id") Integer id) {
        Abteilung a = Abteilung.findById(id);
        if (a == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else {
            return Response.status(Response.Status.OK).entity(a).build();
        }
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

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteAbteilung(@PathParam("id") Integer id) {
        Abteilung abteilung = Abteilung.findById(id);
        if (abteilung == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else {
            abteilung.delete();
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @DELETE
    @Path("/all")
    @Transactional
    public Response deleteAbteilung() {
        List<Abteilung> abteilungen = Abteilung.listAll();
        if (abteilungen.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else {
            for (Abteilung abteilung : abteilungen) {
                abteilung.delete();
            }
            return Response.status(Response.Status.OK).build();
        }
    }
}