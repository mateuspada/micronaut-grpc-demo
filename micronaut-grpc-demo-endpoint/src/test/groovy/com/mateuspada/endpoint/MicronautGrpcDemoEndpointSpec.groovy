package com.mateuspada.endpoint


import io.micronaut.test.extensions.spock.annotation.MicronautTest
import kotlin.coroutines.Continuation
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class MicronautGrpcDemoEndpointSpec extends Specification {

    @Inject
    MicronautGrpcDemoEndpoint micronautGrpcDemoEndpoint

    void 'test request with name Mateus'() {
        when:
        def response = micronautGrpcDemoEndpoint.getDemo(getMicronautRequest(), _ as Continuation) as MicronautGrpcDemoReply

        then:
        response.getMessage() == "Teste de mensagem do Mateus"
    }

    MicronautGrpcDemoRequest getMicronautRequest() {
        return MicronautGrpcDemoRequest.newBuilder().setName("Mateus").build()
    }

}