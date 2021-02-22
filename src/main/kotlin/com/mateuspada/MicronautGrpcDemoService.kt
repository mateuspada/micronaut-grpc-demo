package com.mateuspada

interface MicronautGrpcDemoService {

    fun getDemo(name: String, message: String): MicronautGrpcDemoReply
}