package api;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jpa.Abteilung;
import jpa.Building;

import java.util.List;

@Path("/buildings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BuildingResource {

    @GET
    public List<Building> getAllBuilding() {
        return Building.listAll();
    }

    @POST
    @Transactional
    public Response createBuilding(Building building) {
        building.persist();
        return Response.created(null).entity(building).build();
        //return Response.status(Response.Status.CREATED).entity(building).build();
    }

    @GET
    @Path("/{id}")
    public Building getBuilding(@PathParam("id") Integer id) {
        return Building.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateBuilding(@PathParam("id") Integer id, Building building) {
        boolean changed = false;
        Building existingBuilding = Building.findById(id);
        if (existingBuilding == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (building.getKuerzel() != null) {
            existingBuilding.setKuerzel(building.getKuerzel());
            changed = true;
        }
        if (building.getName() != null) {
            existingBuilding.setName(building.getName());
            changed = true;
        }
        if(changed) {
            existingBuilding.persist();
            return Response.ok(existingBuilding).build();
        }else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteBuilding(@PathParam("id") Integer id) {
        Building existingBuilding = Building.findById(id);
        if (existingBuilding == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingBuilding.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/all")
    @Transactional
    public Response deleteAbteilung() {
        List<Building> buildings = Building.listAll();
        if (buildings.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else {
            for (Building building : buildings) {
                building.delete();
            }
            return Response.status(Response.Status.OK).build();
        }
    }
}