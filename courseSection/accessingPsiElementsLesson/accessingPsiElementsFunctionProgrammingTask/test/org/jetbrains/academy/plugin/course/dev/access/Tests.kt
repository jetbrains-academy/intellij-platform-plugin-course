package org.jetbrains.academy.plugin.course.dev.access

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class Test : BasePlatformTestCase() {
    fun getResourceFileContent(path: String): String {
        val inputStream = Test::class.java.getResourceAsStream(path)
        return inputStream?.bufferedReader()?.use { it.readText() } ?: ""
    }

    fun testSolution() {
        // TODO: add more test cases
        val path = "/sampleProject/src/main/kotlin/AccessingPsiElements/FunctionCounter/MultipleMethods.kt"
        val fileContent = getResourceFileContent(path)
        val file = myFixture.configureByText("MyClass.kt", fileContent)

        val psiFunctionsCount = countKtFunctions(file)
        val authorFunctionsCount = authorCountKtFunctions(file)
        assertEquals("For the Kotlin file with content $fileContent the function countKtClasses should return 6, but currently it returns $psiFunctionsCount", authorFunctionsCount, psiFunctionsCount)
    }
}
