package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.kotlin.psi.*

class ReplaceFunctionCallArgumentsTest : BasePlatformTestCase() {

    fun testReplaceFunctionCallArgumentsWithDataClass() {
        // Setup the Kotlin file content with a function and its calls
        val fileContent = """
            fun testFunction1(data: DataClass) {
                println(data.param1)
                println(data.param2)
            }

            fun main() {
                testFunction1("Hello", 42)
            }
            
            data class DataClass(val param1: String, val param2: Int)
        """.trimIndent()


        val file = myFixture.configureByText("MyFile.kt", fileContent) as KtFile
        val functionName = PsiTreeUtil.findChildOfType(file, KtNamedFunction::class.java)


        if (functionName != null) {
            replaceFunctionCallArgumentsWithDataClass(project, file, functionName, "DataClass")
        }

        // Check the refactored file content
        val expectedFileContent = """
            fun testFunction1(data: DataClass) {
                println(data.param1)
                println(data.param2)
            }

            fun main() {
                testFunction1(DataClass("Hello", 42))
            }
            
            data class DataClass(val param1: String, val param2: Int)
        """.trimIndent()

        // Assert the file content has been refactored as expected
        assertEquals("File content does not match expected refactoring", expectedFileContent, file.text)
    }
}
