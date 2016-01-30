package com.sevenlist.wildfly.rest.resource

import com.sevenlist.wildfly.model.Person

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Response

import static javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path('/persons')
interface PersonResource {

    @POST
    @Consumes(APPLICATION_JSON)
    Response addPerson(Person person)

    @GET
    Collection getPersons()
}