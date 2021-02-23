package com.mateuspada.endpoint

import io.micronaut.context.annotation.Property
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import kotlin.coroutines.Continuation
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
@Property(name = "message", value = "Olá")
class MicronautGrpcDemoEndpointSpec extends Specification {

    @Inject
    MicronautGrpcDemoEndpoint micronautGrpcDemoEndpoint

    void 'test request with name Mateus'() {
        when:
        def response = micronautGrpcDemoEndpoint.getDemo(getMicronautRequest(), _ as Continuation) as MicronautGrpcDemoReply

        then:
        response.getMessage() == "Olá Mateus"
    }

    MicronautGrpcDemoRequest getMicronautRequest() {
        return MicronautGrpcDemoRequest.newBuilder().setName("Mateus").build()
    }

}