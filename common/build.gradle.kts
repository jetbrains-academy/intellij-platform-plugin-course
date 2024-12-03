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
    }
}
