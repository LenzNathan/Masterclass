package api;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jpa.Tag;

import java.util.List;

@Path("/tags")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TagResource {

    @GET
    public List<Tag> getAllTags() {
        return Tag.listAll();
    }

    @POST
    @Transactional
    public Response createTag(Tag tag) {
        tag.persist();
        return Response.status(Response.Status.CREATED).entity(tag).build();
    }

    @GET
    @Path("/{id}")
    public Response getTag(@PathParam("id") Integer id) {
        Tag t = Tag.findById(id);
        if (t == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(t).build();
        }
    }

    /* no set method in Tag.java
        @PUT
        @Path("/{id}")
        @Transactional
        public Response updateTag(@PathParam("id") Integer id, Tag tag) {
            boolean changed = false;
            Tag existingTag = Tag.findById(id);

            if (existingTag == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            if (tag.getName() != null) {
                existingTag.setName(tag.getTagName());
                changed = true;
            }

            if (changed) {
                existingTag.persist();
                return Response.ok(existingTag).build();
            } else {
                return Response.status(Response.Status.NOT_MODIFIED).build();
            }
        }
    */
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteTag(@PathParam("id") Integer id) {
        Tag existingTag = Tag.findById(id);
        if (existingTag == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingTag.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @DELETE
    @Path("/all")
    @Transactional
    public jakarta.ws.rs.core.Response deleteTags() {
        List<Tag> tags = Tag.listAll();
        if (tags.isEmpty()) {
            return Response.status(jakarta.ws.rs.core.Response.Status.NOT_FOUND).build();
        } else {
            try {
                for (Tag tag : tags) {
                    tag.delete();
                }
            } catch (Exception e) {
                return Response.status(Response.Status.CONFLICT).entity("Es ist nicht möglich alle Tags zu löschen," +
                        "da manche von anderen Tabellen referenziert werden").build();
            }
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.OK).build();
        }
    }
}