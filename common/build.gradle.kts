import org.jetbrains.intellij.platform.gradle.TestFrameworkType

group = rootProject.group
version = rootProject.version

val ideaVersion: String by project

plugins {
    id("org.jetbrains.intellij.platform.module")
}

intellijPlatform {
    instrumentCode = false
}

repositories {
    intellijPlatform {
        defaultRepositories()
        jetbrainsRuntime()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity(ideaVersion, useInstaller = false)
        jetbrainsRuntime()
        bundledPlugins("com.intellij.java", "org.jetbrains.kotlin")
        testFramework(TestFrameworkType.Bundled)
    }

    testFixturesApi("junit:junit:4.13.2")
}
