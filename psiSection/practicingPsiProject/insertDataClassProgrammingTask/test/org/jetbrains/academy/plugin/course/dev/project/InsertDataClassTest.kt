package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.kotlin.psi.KtNamedFunction

class InsertDataClassTest : BasePlatformTestCase() {

    fun testSolution() {
        // TODO: add more test cases
        val fileContent = """
            fun testFunction1(param1: String, param2: Int) {
               // Function body
            }    
        """.trimIndent()
        val file = myFixture.configureByText("MyFile.kt", fileContent)
        val functions = PsiTreeUtil.findChildrenOfType(file, KtNamedFunction::class.java)
        for (ktFunction in functions) {
            val arguments = extractFunctionArguments(ktFunction)
            val dataClass = createDataClass("DataClass", arguments)
            insertDataClass(dataClass, file)

            val expected = """fun testFunction1(param1: String, param2: Int) {
   // Function body
}

data class DataClass(
        val param1: String,
        val param2: Int
)"""

            // Verify the data class was added to the file
            assertEquals("Data class not found or incorrect structure \n ${file.text} \n and it should be \n $expected", file.text, expected)

        }
    }
}
