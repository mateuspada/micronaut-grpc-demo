package com.mateuspada

import io.micronaut.context.annotation.Value
import javax.inject.Singleton

@Singleton
class MicronautGrpcDemoEndpoint(
    private val micronautGrpcDemoService: MicronautGrpcDemoService,
    @Value("\${message}") private val message: String
) : MicronautGrpcDemoServiceGrpcKt.MicronautGrpcDemoServiceCoroutineImplBase() {

    override suspend fun getDemo(request: MicronautGrpcDemoRequest): MicronautGrpcDemoReply {
        return micronautGrpcDemoService.getDemo(name = request.name, message = message)
    }

}