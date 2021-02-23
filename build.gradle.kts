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

    val submoduleName = this.name.removePrefix("micronaut-grpc-demo-")

    if (submoduleName == "application") {
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
        plugin("groovy")
    }

    // add dependencies
    apply {
        dependencies {
            kapt(platform("io.micronaut:micronaut-bom:$micronautVersion"))
            kapt(kotlin("stdlib", kotlinVersion))

            kapt("io.micronaut:micronaut-inject-java")
            testApi("io.micronaut:micronaut-inject-java")

            testApi("io.micronaut.test:micronaut-test-spock")
            testApi("org.spockframework:spock-core") {
                exclude(group = "org.codehaus.groovy", module = "groovy-all")
            }

            constraints {
                api("io.micronaut.grpc:micronaut-grpc-runtime:2.3.0")

                api("ch.qos.logback:logback-classic:1.2.3")
                api("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.1")

                api("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

                api("org.apache.maven:maven-core:3.6.3")
            }
        }
    }

    apply{
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
    }

    apply{
        micronaut {
            testRuntime("spock2")
            processing {
                incremental(true)
                annotations( "com.mateuspada.$submoduleName.*")
            }
        }
    }

}