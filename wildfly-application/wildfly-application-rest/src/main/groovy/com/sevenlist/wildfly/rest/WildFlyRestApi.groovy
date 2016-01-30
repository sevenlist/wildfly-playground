package com.sevenlist.wildfly.rest

import com.sevenlist.wildfly.rest.resource.PersonResourceBean

import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("/")
class WildFlyRestApi extends Application {

    private Set classes = new HashSet()
    private Set singletons = new HashSet()

    WildFlyRestApi() {
        classes << PersonResourceBean
    }

    @Override
    Set getClasses() {
        classes;
    }

    @Override
    Set getSingletons() {
        singletons;
    }
}