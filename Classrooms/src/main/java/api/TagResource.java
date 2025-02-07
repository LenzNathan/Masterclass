package api;

//import de.example.model.Tag;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/*
@Path("/tags")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TagResource {

    @GET
    public List<Tag> getAllTags() {
        return Tag.listAll();
    }

    @POST
    public Response createTag(Tag tag) {
        tag.persist();
        return Response.status(Response.Status.CREATED).entity(tag).build();
    }

    @GET
    @Path("/{id}")
    public Tag getTag(@PathParam("id") Integer id) {
        return Tag.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateTag(@PathParam("id") Integer id, Tag tag) {
        Tag existingTag = Tag.findById(id);
        if (existingTag == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingTag.tagName = tag.tagName;
        existingTag.persist();
        return Response.ok(existingTag).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTag(@PathParam("id") Integer id) {
        Tag existingTag = Tag.findById(id);
        if (existingTag == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingTag.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

*/