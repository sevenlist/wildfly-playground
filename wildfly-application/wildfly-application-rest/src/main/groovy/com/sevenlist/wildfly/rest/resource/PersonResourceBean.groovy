package com.sevenlist.wildfly.rest.resource

import com.sevenlist.wildfly.jms.PersonMessageProducer
import com.sevenlist.wildfly.jms.PersonsQueueBrowser
import com.sevenlist.wildfly.model.Person

import javax.ejb.EJB
import javax.ejb.Local
import javax.ejb.Stateless
import javax.ws.rs.core.Response

@Stateless
@Local(PersonResource)
class PersonResourceBean implements PersonResource {

    @EJB
    private PersonMessageProducer personMessageProducer

    @EJB
    private PersonsQueueBrowser personsQueueBrowser

    @Override
    Response addPerson(Person person) {
        def id = UUID.randomUUID().toString()
        personMessageProducer.producePersonMessage(id, person)

        def location = URI.create(id)
        Response.created(location).build()
    }

    @Override
    Collection getPersons() {
        personsQueueBrowser.getPersons()
    }
}
