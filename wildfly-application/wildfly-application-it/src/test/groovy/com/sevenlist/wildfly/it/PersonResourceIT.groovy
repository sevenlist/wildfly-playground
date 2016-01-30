package com.sevenlist.wildfly.it

import com.sevenlist.wildfly.model.Person
import org.apache.commons.lang3.RandomStringUtils
import org.codehaus.jackson.jaxrs.JacksonJsonProvider
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Response

import static javax.ws.rs.client.Entity.json
import static javax.ws.rs.core.Response.Status.CREATED

@Stepwise
class PersonResourceIT extends Specification {

    @Shared
    Person person = createPerson()

    Client client
    WebTarget webTarget
    Response response

    def setup() {
        client = ClientBuilder.newClient()
        client.register(JacksonJsonProvider)
        webTarget = client.target('http://localhost:8080/persons')
    }

    def cleanup() {
        response?.close()
        client.close()
    }

    def "adds person"() {
        when:
        response = webTarget.request().post(json(person))

        then:
        response.status == CREATED.statusCode
    }

    private Person createPerson() {
        def firstName = RandomStringUtils.randomAlphabetic(7).toLowerCase()
        def lastName = RandomStringUtils.randomAlphabetic(7).toLowerCase()
        new Person(firstName: firstName, lastName: lastName)
    }
}
