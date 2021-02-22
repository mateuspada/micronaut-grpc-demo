package com.mateuspada

import javax.inject.Singleton

@Singleton
class MicronautGrpcDemoEndpoint(private val micronautGrpcDemoService: MicronautGrpcDemoService) :
    MicronautGrpcDemoServiceGrpcKt.MicronautGrpcDemoServiceCoroutineImplBase() {

    override suspend fun getDemo(request: MicronautGrpcDemoRequest): MicronautGrpcDemoReply {
        return micronautGrpcDemoService.getDemo(name = request.name, message = "Hello World")
    }

}