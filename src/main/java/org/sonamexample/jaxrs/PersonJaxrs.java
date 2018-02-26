package org.sonamexample.jaxrs;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import org.sonamexample.persistence.jpa.entity.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/person")
public interface PersonJaxrs {
    //this will retrieve all Person with fullName
    @GET
    @Path("/fullname/{fullName}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Person> getPersons(@PathParam("fullName")String fullName);

    //this will get Person by id
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Person getPerson(@PathParam("id")Long id);

    //add a person to PersonRepository
    @POST
    @Path("/add/{fullName}")
    @Produces(MediaType.APPLICATION_JSON)
    Person addPerson(@PathParam("fullName")String fullName);
}
