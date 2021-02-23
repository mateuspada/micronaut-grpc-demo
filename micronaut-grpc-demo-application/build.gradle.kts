plugins {
    kotlin("jvm")
    id("groovy")
}

val micronautVersion: String by project

 micronaut {
    runtime("netty")
    testRuntime("spock2")
    processing {
        incremental(true)
        annotations("com.mateuspada.application.*")
    }
}

dependencies {
    kapt(platform(project(":micronaut-grpc-demo-dependencies")))
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

java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}