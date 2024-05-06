package org.jetbrains.academy.test

import com.intellij.testFramework.fixtures.BasePlatformTestCase

abstract class MyBaseTest : BasePlatformTestCase() {
    protected abstract val resourceClass: Class<*>

    protected fun getResourceFileContent(relativePath: String): String =
        resourceClass.getResourceAsStream(BASE_PATH + relativePath)?.bufferedReader()?.use { it.readText() } ?: ""

    protected fun getFile(fileContent: String) =
        myFixture.configureByText("MyClass.kt", fileContent) ?: error("Internal course error!")

    companion object {
        private const val BASE_PATH =
            "/backUpProjects/psi-final-project/src/main/kotlin/org/jetbrains/academy/plugin/course/dev/project/examples/"
    }
}
