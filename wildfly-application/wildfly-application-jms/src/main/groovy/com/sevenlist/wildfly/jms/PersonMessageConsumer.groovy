package com.sevenlist.wildfly.jms

import groovy.util.logging.Slf4j

import javax.ejb.ActivationConfigProperty
import javax.ejb.MessageDriven
import javax.jms.Message
import javax.jms.MessageListener

@MessageDriven(activationConfig = [
        @ActivationConfigProperty(
                propertyName = 'destinationType',
                propertyValue = 'javax.jms.Queue'),
        @ActivationConfigProperty(
                propertyName = 'destinationLookup',
                propertyValue = 'java:global/jms/queue/persons')
])
@Slf4j
class PersonMessageConsumer implements MessageListener {

    @Override
    void onMessage(Message message) {
        log.info('onMessage: {}', message)
    }
}
