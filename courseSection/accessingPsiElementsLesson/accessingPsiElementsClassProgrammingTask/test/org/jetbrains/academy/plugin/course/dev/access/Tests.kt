package org.jetbrains.academy.plugin.course.dev.access

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class Test : BasePlatformTestCase() {
    fun getResourceFileContent(path: String): String {
        val inputStream = Test::class.java.getResourceAsStream(path)
        return inputStream?.bufferedReader()?.use { it.readText() } ?: ""
    }

    fun testSolution() {
        // TODO: add more test cases
        val path = "/sampleProject/src/main/kotlin/AccessingPsiElements/ClassCounter/MultipleClasses.kt"
        val fileContent = getResourceFileContent(path)
        val file = myFixture.configureByText("MyClass.kt", fileContent)

        val psiClassesCount = countKtClasses(file)
        val authorClassesCount = authorCountKtClasses(file)
        assertEquals("For the Kotlin file with content $fileContent the function countKtClasses should return 2, but currently it returns $psiClassesCount", authorClassesCount, psiClassesCount)
    }
}
