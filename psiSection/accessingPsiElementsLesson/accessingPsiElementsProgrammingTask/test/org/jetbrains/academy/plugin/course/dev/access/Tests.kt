package org.jetbrains.academy.plugin.course.dev.access

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.academy.plugin.course.dev.ui.Counter

abstract class BaseCounterTest(private val basePath: String, val counter: Counter) : BasePlatformTestCase() {
    private fun getResourceFileContent(relativePath: String) =
        Test::class.java.getResourceAsStream(basePath + relativePath)?.bufferedReader()?.use { it.readText() } ?: ""

    private fun getFile(fileContent: String) =
        myFixture.configureByText("MyClass.kt", fileContent) ?: error("Internal course error!")

    fun doTest(relativePath: String) {
        val fileContent = getResourceFileContent(relativePath)
        val file = getFile(fileContent)

        val userCount = counter.counterFromStudent(file)
        val authorCount = counter.counterFromAuthor(file)
        assertEquals(
            "For the Kotlin file with content $fileContent the function that calculates the number of ${counter.entityName}s should return $authorCount, but currently it returns $userCount",
            authorCount,
            userCount
        )
    }
}

class Test : BaseCounterTest(BASE_PATH, Counter.Class) {

    fun testSolution() {
        // TODO: add more test cases
        doTest("MultipleClasses.kt")
    }

    companion object {
        private const val BASE_PATH = "/psiElementsCountProject/src/main/kotlin/AccessingPsiElements/ElementsCounter/"
    }
}

class FunctionCounterTest : BaseCounterTest(BASE_PATH, Counter.Function) {

    fun testSolution() {
        // TODO: add more test cases
        doTest("AlphabeticalSortExample.kt")
    }

    companion object {
        private const val BASE_PATH = "/SortMethodsProject/src/main/kotlin/EditingPsiElements/"
    }
}
