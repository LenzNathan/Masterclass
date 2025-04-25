package api;

import jpa.Schulklasse;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/schulklassen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SchulklasseResource {

    @GET
    public List<Schulklasse> getAllSchulklassen() {
        return Schulklasse.listAll();
    }

    @POST
    @Transactional
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
    @Transactional
    public Response updateSchulklasse(@PathParam("id") Integer id, Schulklasse schulklasse) {
        Schulklasse existingSchulklasse = Schulklasse.findById(id);
        if (existingSchulklasse == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        boolean changed = false;

        // Update 'kennung' if it's different from the existing one
        if (existingSchulklasse.getKennung() != null && !existingSchulklasse.getKennung().equals(schulklasse.getKennung())) {
            existingSchulklasse.setKennung(schulklasse.getKennung());
            changed = true;
        }

        // Update 'nickname' if it's different from the existing one
        if (existingSchulklasse.getNickname() != null && !existingSchulklasse.getNickname().equals(schulklasse.getNickname())) {
            existingSchulklasse.setNickname(schulklasse.getNickname());
            changed = true;
        }

        // Update 'abteilung' if it's different from the existing one
        if (existingSchulklasse.getAbteilung() != null && !existingSchulklasse.getAbteilung().equals(schulklasse.getAbteilung())) {
            existingSchulklasse.setAbteilung(schulklasse.getAbteilung());
            changed = true;
        }

        // Update 'jahrgang' if it's different from the existing one
        if (existingSchulklasse.getJahrgang() != null && !existingSchulklasse.getJahrgang().equals(schulklasse.getJahrgang())) {
            existingSchulklasse.setJahrgang(schulklasse.getJahrgang());
            changed = true;
        }

        // Persist changes only if something has changed
        if (changed) {
            existingSchulklasse.persist();
            return Response.status(Response.Status.OK).entity(existingSchulklasse).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteSchulklasse(@PathParam("id") Integer id) {
        Schulklasse existingSchulklasse = Schulklasse.findById(id);
        if (existingSchulklasse == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingSchulklasse.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @DELETE
    @Path("/all")
    @Transactional
    public jakarta.ws.rs.core.Response deleteSchulKlassen() {
        List<Schulklasse> schulklasses = Schulklasse.listAll();
        if (schulklasses.isEmpty()){
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.NOT_FOUND).build();
        }else {
            for (Schulklasse schulklasse : schulklasses) {
                schulklasse.delete();
            }
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.OK).build();
        }
    }
}