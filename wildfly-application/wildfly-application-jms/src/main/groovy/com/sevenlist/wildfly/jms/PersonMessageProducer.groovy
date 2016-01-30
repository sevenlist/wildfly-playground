package com.sevenlist.wildfly.jms

import com.sevenlist.wildfly.model.Person

import javax.annotation.Resource
import javax.ejb.Stateless
import javax.inject.Inject
import javax.jms.Destination
import javax.jms.JMSContext
import javax.jms.MapMessage

@Stateless
class PersonMessageProducer {

    @Resource(mappedName = 'java:global/jms/queue/persons')
    private Destination personsQueue

    @Inject
    private JMSContext jmsContext

    void producePersonMessage(String id, Person person) {
        MapMessage message = createPersonMessage(id, person)
        jmsContext.createProducer().send(personsQueue, message)
    }

    private MapMessage createPersonMessage(String id, Person person) {
        MapMessage personMessage = jmsContext.createMapMessage()
        personMessage.setString('id', id)
        personMessage.setString('firstName', person.firstName)
        personMessage.setString('lastName', person.lastName)
        personMessage
    }
}
