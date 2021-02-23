import com.google.protobuf.gradle.*

plugins {
    kotlin("jvm")
    id("com.google.protobuf") version "0.8.13"
}

val protocVersion: String by project
val grpcVersion: String by project
val grpcKotlinVersion: String by project

micronaut {
    runtime("netty")
    testRuntime("spock2")
    processing {
        incremental(true)
        annotations("com.mateuspada.endpoint.*")
    }
}

dependencies {
    kapt(platform(project(":micronaut-grpc-demo-dependencies")))
    kapt("io.micronaut:micronaut-inject-java")

    api("io.micronaut.grpc:micronaut-grpc-runtime")
    api("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")

    testAnnotationProcessor("io.micronaut:micronaut-inject-java")
    testAnnotationProcessor("io.micronaut:micronaut-validation")
    testImplementation("io.micronaut:micronaut-inject-java")
}

sourceSets {
    main {
        java {
            srcDirs("build/generated/source/proto/main/grpc")
            srcDirs("build/generated/source/proto/main/grpckt")
            srcDirs("build/generated/source/proto/main/java")
        }
    }
}

protobuf {
    protoc { artifact = "com.google.protobuf:protoc:$protocVersion" }
    plugins {
        id("grpc") { artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion" }
        id("grpckt") { artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk7@jar" }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without options.
                id("grpc")
                id("grpckt")
            }
        }
    }
}