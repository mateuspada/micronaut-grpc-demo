plugins {
    `java-platform`
    id("org.jetbrains.kotlin.jvm") version "1.4.30" apply false
    id("org.jetbrains.kotlin.kapt") version "1.4.30" apply false
    id("io.micronaut.library") version "1.3.4" apply false
    id("io.micronaut.application") version "1.3.4" apply false
}

val kotlinVersion: String by project
val micronautVersion: String by project
val projectGroupId: String by project
val projectVersion: String by project

group = projectGroupId
version = projectVersion

javaPlatform {
    allowDependencies()
}

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
    api(platform(project(":micronaut-grpc-demo-dependencies")))

    constraints {
        subprojects.forEach { project ->
            if (project.name != "micronaut-grpc-demo-dependencies") {
                api(project(":" + project.name))
            }
        }
    }
}

subprojects {

    if (this.name == "micronaut-grpc-demo-application") {
        apply {
            plugin("io.micronaut.application")
        }
    } else if (this.name != "micronaut-grpc-demo-dependencies") {
        apply {
            plugin("io.micronaut.library")
        }
    }

    if (this.name != "micronaut-grpc-demo-dependencies") {
        apply {
            plugin("org.jetbrains.kotlin.jvm")
            plugin("org.jetbrains.kotlin.kapt")
        }
    }

}