package com.mammb;

import java.util.List;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.WebApplicationException;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @GET
    public List<Person> get() {
        return personRepository.listAll(Sort.by("name"));
    }

    @POST
    @Transactional
    public Response create(Person person) {
        if (person.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        personRepository.persist(person);
        return Response.ok(person).status(201).build();
    }

    @GET
    public long count(){
        return personRepository.count();
    }

}
