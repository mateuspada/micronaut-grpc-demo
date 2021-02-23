package com.mateuspada.endpoint

import io.micronaut.context.annotation.Value
import javax.inject.Singleton

@Singleton
class MicronautGrpcDemoEndpoint(@Value("\${message}") val message: String) :
    MicronautGrpcDemoServiceGrpcKt.MicronautGrpcDemoServiceCoroutineImplBase() {

    override suspend fun getDemo(request: MicronautGrpcDemoRequest): MicronautGrpcDemoReply {
        return MicronautGrpcDemoReply
            .newBuilder()
            .setMessage("$message ${request.name}")
            .build()
    }
}