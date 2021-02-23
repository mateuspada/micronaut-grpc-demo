plugins {
    kotlin("jvm")
}

dependencies {
    kapt("io.micronaut:micronaut-inject-java")
    api(project(":micronaut-grpc-demo-endpoint"))

    api("io.micronaut.grpc:micronaut-grpc-runtime")

    testAnnotationProcessor("io.micronaut:micronaut-inject-java")
    testAnnotationProcessor("io.micronaut:micronaut-validation")
    testImplementation("io.micronaut:micronaut-inject-java")
}

application {
    mainClass.set("com.mateuspada.application.ApplicationKt")
}