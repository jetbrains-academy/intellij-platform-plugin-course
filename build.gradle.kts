import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun gradleProperties(key: String) = providers.gradleProperty(key)

group = gradleProperties("courseGroup").get()
version = gradleProperties("courseVersion").get()

plugins {
    java
    val kotlinVersion = "1.9.21"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
    id("org.jetbrains.intellij") version "1.16.1"
}

intellij {
    version.set("2023.1.2")
    type.set("IC")
    plugins.set(listOf("com.intellij.java", "org.jetbrains.kotlin"))
    downloadSources.set(true)
    updateSinceUntilBuild.set(true)
}

val detektReportMerge by tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
    output.set(rootProject.buildDir.resolve("reports/detekt/merge.sarif"))
}

allprojects {
    repositories {
        mavenCentral()
        maven {
            // To be able to use the Kotlin test framework for the tests - https://github.com/jetbrains-academy/kotlin-test-framework
            url = uri("https://packages.jetbrains.team/maven/p/kotlin-test-framework/kotlin-test-framework")
        }
    }
}


tasks {
    wrapper {
        gradleVersion = gradleProperties("gradleVersion").get()
    }
}

// Configure dependencies for all gradle modules
configure(subprojects) {
    apply<io.gitlab.arturbosch.detekt.DetektPlugin>()

    apply {
        plugin("java")
        plugin("kotlin")
        plugin("org.jetbrains.intellij")
    }

    intellij {
        version.set("2023.1.2")
        type.set("IC")
        plugins.set(listOf("com.intellij.java", "org.jetbrains.kotlin"))
        downloadSources.set(true)
        updateSinceUntilBuild.set(true)
    }

    // Configure detekt
    configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        config = rootProject.files("detekt.yml")
        buildUponDefaultConfig = true
        debug = true
    }
    tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
        finalizedBy(detektReportMerge)
        reports.sarif.required.set(true)
        detektReportMerge.get().input.from(sarifReportFile)
    }
    tasks.getByPath("detekt").onlyIf { project.hasProperty("runDetekt") }

    // Include dependencies
    dependencies {
        // By default, only the core module is included
        implementation("org.jetbrains.academy.test.system:core:2.0.5")

        val detektVersion = "1.22.0"
        implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion")
        implementation("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
    }

    val jvmVersion = gradleProperties("jvmVersion").get()
    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = jvmVersion
            }
        }
        withType<JavaCompile> {
            sourceCompatibility = jvmVersion
            targetCompatibility = jvmVersion
        }

        // This part is necessary for the JetBrains Academy plugin
        withType<Test> {
            outputs.upToDateWhen { false }

            addTestListener(object : TestListener {
                override fun beforeSuite(suite: TestDescriptor) {}
                override fun beforeTest(testDescriptor: TestDescriptor) {}
                override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
                    if (result.resultType == TestResult.ResultType.FAILURE) {
                        val message = result.exception?.message ?: "Wrong answer"
                        val lines = message.split("\n")
                        println("#educational_plugin FAILED + ${lines[0]}")
                        lines.subList(1, lines.size).forEach { line ->
                            println("#educational_plugin$line")
                        }
                        // we need this to separate output of different tests
                        println()
                    }
                }

                override fun afterSuite(suite: TestDescriptor, result: TestResult) {}
            })
        }
    }
}

// We have to store tests inside test folder directly
configure(subprojects.filter { it.name != "common" }) {

    val jvmVersion = gradleProperties("jvmVersion").get()
    tasks {
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = jvmVersion
            }
        }

        withType<JavaCompile> {
            sourceCompatibility = jvmVersion
            targetCompatibility = jvmVersion
        }

        withType<org.jetbrains.intellij.tasks.BuildSearchableOptionsTask>()
            .forEach { it.enabled = false }
    }

    sourceSets {
        getByName("main").java.srcDirs("src")
        getByName("test").java.srcDirs("test")
    }

    dependencies {
        implementation(project(":common"))
    }

    tasks.register<Copy>("restoreOriginalFiles") {
        from("${rootDir}/common/src/main/resources/backUpProjects")
        into("${rootDir}/common/src/main/resources/projects")
    }

    tasks {
        runIde {
            dependsOn("restoreOriginalFiles")
        }
    }

}
