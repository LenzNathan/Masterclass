package api;

import jpa.Student;
import jpa.Subject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/subjects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubjectResource {

    @GET
    public List<Subject> getAllSubjects() {
        return Subject.listAll();
    }

    @POST
    @Transactional
    public Response createSubject(Subject subject) {
        subject.persist();
        return Response.status(Response.Status.CREATED).entity(subject).build();
    }

    @GET
    @Path("/{id}")
    public Subject getSubject(@PathParam("id") Integer id) {
        return Subject.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateSubject(@PathParam("id") Integer id, Subject subject) {
        boolean changed = false;
        Subject existingSubject = Subject.findById(id);

        if (existingSubject == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Update 'name' if it's different from the existing one
        if (subject.getName() != null && !subject.getName().equals(existingSubject.getName())) {
            existingSubject.setName(subject.getName());
            changed = true;
        }

        // Update 'hoursPerWeek' if it's different from the existing one
        if (subject.getHoursPerWeek() != existingSubject.getHoursPerWeek()) {
            existingSubject.setHoursPerWeek(subject.getHoursPerWeek());
            changed = true;
        }

        // Update 'maxHoursPerDay' if it's different from the existing one
        if (subject.getMaxHoursPerDay() != existingSubject.getMaxHoursPerDay()) {
            existingSubject.setMaxHoursPerDay(subject.getMaxHoursPerDay());
            changed = true;
        }

        // Update 'minHoursPerDay' if it's different from the existing one
        if (subject.getMinHoursPerDay() != existingSubject.getMinHoursPerDay()) {
            existingSubject.setMinHoursPerDay(subject.getMinHoursPerDay());
            changed = true;
        }

        // Update 'tagsUseful' if it's different from the existing one
        if (subject.getTagsUseful() != null && !subject.getTagsUseful().equals(existingSubject.getTagsUseful())) {
            existingSubject.setTagsUseful(subject.getTagsUseful());
            changed = true;
        }

        // Update 'tagsRequired' if it's different from the existing one
        if (subject.getTagsRequired() != null && !subject.getTagsRequired().equals(existingSubject.getTagsRequired())) {
            existingSubject.setTagsRequired(subject.getTagsRequired());
            changed = true;
        }

        // Persist changes only if something has changed
        if (changed) {
            existingSubject.persist();
            return Response.status(Response.Status.OK).entity(existingSubject).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public javax.ws.rs.core.Response deleteStudent(@PathParam("id") Integer id) {
        Subject existingSubject = Subject.findById(id);
        if (existingSubject == null) {
            return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
        }
        existingSubject.delete();
        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/all")
    @Transactional
    public jakarta.ws.rs.core.Response deleteAbteilung() {
        List<Subject> subjects = Subject.listAll();
        if (subjects.isEmpty()){
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.NOT_FOUND).build();
        }else {
            for (Subject subject : subjects) {
                subject.delete();
            }
            return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.OK).build();
        }
    }
}
