package com.beesion.ms.test.resource;

import com.beesion.ms.model.Address;
import com.beesion.ms.test.repository.AddressRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Recurso REST simple para Address
 */
@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

    @Inject
    AddressRepo addressRepo;

    @GET
    public List<Address> getAllAddresses() {
        return addressRepo.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getAddressById(@PathParam("id") Long id) {
        Address address = addressRepo.findById(id);
        if (address == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(address).build();
    }

    @POST
    @Transactional
    public Response createAddress(Address address) {
        try {
            // Crear nueva instancia limpia
            Address newAddress = new Address();
            newAddress.setStreet(address.getStreet());
            newAddress.setCity(address.getCity());
            newAddress.setState(address.getState());
            newAddress.setPostalCode(address.getPostalCode());
            newAddress.setCountry(address.getCountry());
            newAddress.setPersonId(address.getPersonId());
            
            addressRepo.persist(newAddress);
            return Response.status(Response.Status.CREATED).entity(newAddress).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateAddress(@PathParam("id") Long id, Address address) {
        try {
            Address existingAddress = addressRepo.findById(id);
            if (existingAddress == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            
            existingAddress.setStreet(address.getStreet());
            existingAddress.setCity(address.getCity());
            existingAddress.setState(address.getState());
            existingAddress.setPostalCode(address.getPostalCode());
            existingAddress.setCountry(address.getCountry());
            existingAddress.setPersonId(address.getPersonId());
            
            return Response.ok(existingAddress).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteAddress(@PathParam("id") Long id) {
        try {
            Address address = addressRepo.findById(id);
            if (address == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            
            addressRepo.delete(address);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage()).build();
        }
    }
}