plugins {
    `java-platform`
}

javaPlatform {
    allowDependencies()
}

val micronautVersion: String by project
val kotlinVersion: String by project
val grpcKotlinVersion: String by project

dependencies {

    api(platform("io.micronaut:micronaut-bom:$micronautVersion"))

    api(kotlin("stdlib", kotlinVersion))

    constraints {
        api("io.micronaut.grpc:micronaut-grpc-runtime:2.3.0")

        api("ch.qos.logback:logback-classic:1.2.3")
        api("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.1")

        api("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
//        api("com.amazonaws:aws-java-sdk-core:1.11.959")

        api("org.apache.maven:maven-core:3.6.3")

        api(project(":micronaut-grpc-demo-application"))
        api(project(":micronaut-grpc-demo-endpoint"))
    }
}
