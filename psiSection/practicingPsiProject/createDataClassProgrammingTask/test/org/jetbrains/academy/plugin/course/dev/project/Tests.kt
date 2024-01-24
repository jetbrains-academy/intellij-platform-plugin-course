package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.kotlin.psi.KtNamedFunction

class GenerateDataClassTest : BasePlatformTestCase() {

    fun testGenerateDataClassFromFunction() {
        // Initialize the Kotlin PSI factory
        val fileContent = """
            fun testFunction1(param1: String, param2: Int) {
    // Function body
        }    
        """.trimIndent()
        val file = myFixture.configureByText("MyFile.kt", fileContent)
        val functions = PsiTreeUtil.findChildrenOfType(file, KtNamedFunction::class.java)
        for (ktFunction in functions) {
            val arguments = extractFunctionArguments(ktFunction)
            val dataClass = createDataClass(arguments)
            val expectedDataClass = """
                data class DataClass(
                    val param1: String,
                    val param2: Int
                )
            """.trimIndent()
            assertEquals("Generated data class does not match the expected result.", expectedDataClass, dataClass.trim())
        }
    }
}
