package com.mateuspada

import javax.inject.Singleton

@Singleton
class MicronautGrpcDemoServiceImpl : MicronautGrpcDemoService {
    override fun getDemo(name: String, message: String): MicronautGrpcDemoReply {
        return MicronautGrpcDemoReply
            .newBuilder()
            .setMessage("$message $name")
            .build()
    }
}