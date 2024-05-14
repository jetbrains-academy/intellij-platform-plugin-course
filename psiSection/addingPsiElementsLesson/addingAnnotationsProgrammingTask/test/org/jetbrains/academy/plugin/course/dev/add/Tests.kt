package org.jetbrains.academy.plugin.course.dev.add

import com.intellij.testFramework.fixtures.BasePlatformTestCase

abstract class BaseAddAnnotationsTest : BasePlatformTestCase() {
    private fun getResourceFileContent(relativePath: String) =
        AddAnnotationsTest::class.java.getResourceAsStream(BASE_PATH + relativePath)?.bufferedReader()?.use { it.readText() } ?: ""
    private fun getFile(fileContent: String) =
        myFixture.configureByText("MyClass.kt", fileContent) ?: error("Internal course error!")

    fun doTest(relativePath: String) {
        val fileContent = getResourceFileContent(relativePath)
        val file = getFile(fileContent)
        val authorFile = getFile(fileContent)
        addDeprecatedAnnotations(file)
        authorAddDeprecatedAnnotations(authorFile)
        assertEquals("Something went wrong! Expected: ${authorFile.text}, but got: ${file.text}", authorFile.text, file.text)
    }

    companion object {
        private const val BASE_PATH = "/backUpProjects/sort-methods-project/src/main/kotlin/org/jetbrains/academy/plugin/course/dev/project/examples/"
    }
}
class AddAnnotationsTest : BaseAddAnnotationsTest() {

    fun testSolution() {
        doTest("AlphabeticalSortExample.kt")
    }
}
