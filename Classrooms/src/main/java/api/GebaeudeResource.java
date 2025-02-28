package api;

import jakarta.ws.rs.core.Response;
import jpa.Building;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/Building")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GebaeudeResource {

    @GET
    public List<Building> getAllBuilding() {
        return Building.listAll();
    }

    @POST
    public Response createBuilding(Building Building) {
        Building.persist();
        return Response.status(Response.Status.CREATED).entity(Building).build();
    }

    @GET
    @Path("/{id}")
    public Building getBuilding(@PathParam("id") Integer id) {
        return Building.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateBuilding(@PathParam("id") Integer id, Building Building) {
        Building existingBuilding = Building.findById(id);
        if (existingBuilding == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingBuilding.kuerzel = Building.kuerzel;
        existingBuilding.name = Building.name;
        existingBuilding.persist();
        return Response.ok(existingBuilding).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteBuilding(@PathParam("id") Integer id) {
        Building existingBuilding = Building.findById(id);
        if (existingBuilding == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        //existingBuilding.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}