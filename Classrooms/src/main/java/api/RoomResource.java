package api;

import jpa.Room;
import jakarta.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    @GET
    public List<Room> getAllRoom() {
        return Room.listAll();
    }

    @POST
    @Transactional
    public Response createRoom(Room raum) {
        raum.persist();
        return Response.status(Response.Status.CREATED).entity(raum).build();
    }

    @GET
    @Path("/{id}")
    public Room getRoom(@PathParam("id") Integer id) {
        return Room.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateRoom(@PathParam("id") Integer id, Room room) {
        Room existingRoom = Room.findById(id);
        if (existingRoom == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        boolean changed = false;

        // Update Room Number if it's different from the existing one
        if (existingRoom.getNumber() != room.getNumber()) {
            existingRoom.setNumber(room.getNumber());
            changed = true;
        }

        // Update Floor if it's different from the existing one
        if (existingRoom.getFloor() != null && !existingRoom.getFloor().equals(room.getFloor())) {
            existingRoom.setFloor(room.getFloor());
            changed = true;
        }

        // Update Capacity if it's different from the existing one
        if (existingRoom.getCapacity() != room.getCapacity()) {
            existingRoom.setCapacity(room.getCapacity());
            changed = true;
        }

        // Update Building if it's different from the existing one
        if (existingRoom.getBuilding() != null && !existingRoom.getBuilding().equals(room.getBuilding())) {
            existingRoom.setBuilding(room.getBuilding());
            changed = true;
        }

        // Update Tags if they are different from the existing ones
        if (existingRoom.getTags() != null && !existingRoom.getTags().equals(room.getTags())) {
            existingRoom.setTags(room.getTags());
            changed = true;
        }

        // Persist changes only if something has changed
        if (changed) {
            existingRoom.persist();
            return Response.status(Response.Status.OK).entity(existingRoom).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteRoom(@PathParam("id") Integer id) {
        Room existingRoom = Room.findById(id);
        if (existingRoom == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingRoom.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}