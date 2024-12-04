package api;

//import de.example.model.SchuelerGruppe;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/schueler-gruppen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SchuelerGruppeResource {

    @GET
    public List<SchuelerGruppe> getAllSchuelerGruppen() {
        return SchuelerGruppe.listAll();
    }

    @POST
    public Response createSchuelerGruppe(SchuelerGruppe schuelerGruppe) {
        schuelerGruppe.persist();
        return Response.status(Response.Status.CREATED).entity(schuelerGruppe).build();
    }

    @GET
    @Path("/{schuelerId}/{groupId}")
    public SchuelerGruppe getSchuelerGruppe(@PathParam("schuelerId") Integer schuelerId, @PathParam("groupId") Integer groupId) {
        return SchuelerGruppe.findById(schuelerId, groupId);
    }

    @DELETE
    @Path("/{schuelerId}/{groupId}")
    public Response deleteSchuelerGruppe(@PathParam("schuelerId") Integer schuelerId, @PathParam("groupId") Integer groupId) {
        SchuelerGruppe existingSchuelerGruppe = SchuelerGruppe.findById(schuelerId, groupId);
        if (existingSchuelerGruppe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingSchuelerGruppe.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
