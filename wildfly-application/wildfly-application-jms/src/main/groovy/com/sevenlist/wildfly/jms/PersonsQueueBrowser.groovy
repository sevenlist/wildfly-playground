package com.sevenlist.wildfly.jms

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import javax.annotation.Resource
import javax.ejb.Stateless
import javax.inject.Inject
import javax.jms.Destination
import javax.jms.JMSContext
import javax.jms.MapMessage
import javax.jms.QueueBrowser

@Stateless
class PersonsQueueBrowser {

    @Resource(mappedName = 'java:global/jms/queue/persons')
    private Destination personsQueue

    @Inject
    private JMSContext jmsContext

    private QueueBrowser queueBrowser

    @PostConstruct
    void init() {
        queueBrowser = jmsContext.createBrowser(personsQueue)
    }

    @PreDestroy
    void close() {
        queueBrowser.close()
    }

    List getPersons() {
        List messages = []
        queueBrowser.enumeration.each() {
            messages < messageAsJson(it)
        }
        messages
    }

    private String messageAsJson(MapMessage mapMessage) {
        String id = mapMessage.getString('id')
        String firstName = mapMessage.getString('firstName')
        String lastName = mapMessage.getString('lastName')
        "{ \"id\": \"$id\", \"firstName\": \"$firstName\", \"lastName\": \"$lastName\" }"
    }
}
