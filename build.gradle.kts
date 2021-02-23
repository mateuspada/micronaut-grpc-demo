plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.30"
    id("org.jetbrains.kotlin.kapt") version "1.4.30"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.30"
    id("io.micronaut.library") version "1.3.4"
    id("io.micronaut.application") version "1.3.4" apply false
}

val kotlinVersion: String by project
val micronautVersion: String by project
val projectGroupId: String by project
val projectVersion: String by project
val grpcKotlinVersion: String by project

group = projectGroupId
version = projectVersion

allprojects {
    group = projectGroupId
    version = projectVersion

    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
    }
}

dependencies {
    constraints {
        subprojects.forEach { project ->
            api(project(":" + project.name))
        }
    }
}

subprojects {

    if (this.name == "micronaut-grpc-demo-application") {
        apply {
            plugin("io.micronaut.application")
        }
    } else {
        apply {
            plugin("io.micronaut.library")
        }
    }

    // Apply jvm plugins
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.kapt")
        plugin("org.jetbrains.kotlin.plugin.allopen")
    }

    // add dependencies
    apply {
        dependencies {
            kapt(platform("io.micronaut:micronaut-bom:$micronautVersion"))
            kapt(kotlin("stdlib", kotlinVersion))

            constraints {
                api("io.micronaut.grpc:micronaut-grpc-runtime:2.3.0")

                api("ch.qos.logback:logback-classic:1.2.3")
                api("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.1")

                api("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
                api("com.amazonaws:aws-java-sdk-core:1.11.959")

                api("org.apache.maven:maven-core:3.6.3")
            }
        }
    }

}