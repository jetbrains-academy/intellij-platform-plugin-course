package org.jetbrains.academy.plugin.course.dev.access

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.academy.plugin.course.dev.ui.Counter

abstract class BaseCounterTest(private val counter: Counter) : BasePlatformTestCase() {
    private fun getResourceFileContent(relativePath: String) =
        Test::class.java.getResourceAsStream(BASE_PATH + relativePath)?.bufferedReader()?.use { it.readText() } ?: ""

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

    companion object {
        private const val BASE_PATH = "/backUpProjects/psi-elements-count-project/src/main/kotlin/org/jetbrains/academy/plugin/course/dev/project/examples/"
    }
}

class Test : BaseCounterTest(Counter.Class) {

    fun testSolution() {
        // TODO: add more test cases
        doTest("MultipleClasses.kt")
        doTest("MultipleMethods.kt")
        doTest("NestedSortExample.kt")
        doTest("NestedClasses.kt")
    }
}

class FunctionCounterTest : BaseCounterTest(Counter.Function) {

    fun testSolution() {
        // TODO: add more test cases
        doTest("MultipleClasses.kt")
        doTest("MultipleMethods.kt")
        doTest("NestedSortExample.kt")
        doTest("NestedClasses.kt")
    }
}
