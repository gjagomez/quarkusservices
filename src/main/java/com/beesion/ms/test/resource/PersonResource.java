package com.beesion.ms.test.resource;

import com.beesion.ms.model.Person;
import com.beesion.ms.test.repository.PersonRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Recurso REST 
 */
@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonRepo personRepo;

    /**
     * Obtiene todas las personas
     */
    @GET
    public List<Person> getAllPersons() {
        return personRepo.listAll();
    }

    /**
     * Obtiene una persona por ID
     */
    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") Long id) {
        Person person = personRepo.findById(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(person).build();
    }

    /**
     * Crea una nueva persona
     */
    @POST
    @Transactional
    public Response createPerson(Person person) {
        try {
            // Validación básica
            if (person.getName() == null || person.getName().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("El nombre es requerido").build();
            }

            // Crear nueva instancia limpia
            Person newPerson = new Person();
            newPerson.setName(person.getName());
            
            // Persistir
            personRepo.persist(newPerson);
            
            return Response.status(Response.Status.CREATED).entity(newPerson).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
        }
    }

    /**
     * Actualiza una persona
     */
    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePerson(@PathParam("id") Long id, Person person) {
        try {
            Person existingPerson = personRepo.findById(id);
            if (existingPerson == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            
            existingPerson.setName(person.getName());
            return Response.ok(existingPerson).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
        }
    }

    /**
     * Elimina una persona
     */
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePerson(@PathParam("id") Long id) {
        try {
            Person person = personRepo.findById(id);
            if (person == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            
            personRepo.delete(person);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
        }
    }
}