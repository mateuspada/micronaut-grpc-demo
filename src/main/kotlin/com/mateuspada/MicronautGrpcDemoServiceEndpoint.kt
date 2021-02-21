package com.mateuspada

import javax.inject.Singleton

@Singleton
class MicronautGrpcDemoServiceEndpoint : MicronautGrpcDemoServiceGrpcKt.MicronautGrpcDemoServiceCoroutineImplBase() {
    override suspend fun getDemo(request: MicronautGrpcDemoRequest): MicronautGrpcDemoReply {
        return MicronautGrpcDemoReply
            .newBuilder()
            .setMessage("Hello World ${request.name}")
            .build()
    }
}