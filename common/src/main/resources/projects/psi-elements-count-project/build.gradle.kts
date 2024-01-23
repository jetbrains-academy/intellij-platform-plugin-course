plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.jetbrains.academy.plugin.course.dev.project.examples"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}