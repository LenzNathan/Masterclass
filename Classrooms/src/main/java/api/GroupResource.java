package api;

import jpa.Group;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/groups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupResource {

    @GET
    public List<Group> getAllGroup() {
        return Group.listAll();
    }

    @POST
    @Transactional
    public Response createGroup(Group group) {
        group.persist();
        return Response.status(Response.Status.CREATED).entity(group).build();
    }

    @GET
    @Path("/{id}")
    public Response getGroup(@PathParam("id") Integer id) {
        Group g = Group.findById(id);
        if (g == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else {
            return Response.status(Response.Status.OK).entity(g).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateGroup(@PathParam("id") Integer id, Group group) {
        boolean changed = false;
        Group existingGroup = Group.findById(id);
        if (existingGroup == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (group.getKennung() != null) {
            existingGroup.setKennung(group.getKennung());
            changed = true;
        }
        if (group.getSchulklasse() != null) {
            existingGroup.setSchulklasse(group.getSchulklasse());
            changed = true;
        }
        if (group.getStudents() != null) {
            existingGroup.setStudents(group.getStudents());
            changed = true;
        }
        if (changed) {
            existingGroup.persist();
            return Response.ok(existingGroup).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteGroup(@PathParam("id") Integer id) {
        Group existingGroup = Group.findById(id);
        if (existingGroup == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            existingGroup.delete();
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @DELETE
    @Path("/all")
    @Transactional
    public Response deleteGroups() {
        List<Group> groups = Group.listAll();
        if (groups.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else {
            for (Group group : groups) {
                group.delete();
            }
            return Response.status(Response.Status.OK).build();
        }
    }
}