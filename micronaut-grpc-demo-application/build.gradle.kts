plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":micronaut-grpc-demo-endpoint"))

    api("io.micronaut.grpc:micronaut-grpc-runtime")
}

application {
    mainClass.set("com.mateuspada.application.ApplicationKt")
}