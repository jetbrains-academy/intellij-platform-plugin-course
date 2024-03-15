package org.jetbrains.academy.plugin.course.dev.edit

import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.kotlin.psi.KtNamedFunction

class Test : BasePlatformTestCase() {

    private fun String.snakeToCamelCase(): String {
        return this.split('_').withIndex().joinToString("") { (index, it) ->
            if (index == 0) it.lowercase() else it.lowercase().replaceFirstChar(Char::titlecase)
        }
    }

    // TODO: make parametrized
    fun testSolution() {
        val fileContent = """
            fun say_hello() {
                println("Hello")
            }
            
            fun say_goodbye() {
                println("Goodbye")
            }
        """.trimIndent()
        val file = myFixture.configureByText("MyClass.kt", fileContent)

        val functions = PsiTreeUtil.findChildrenOfType(file, KtNamedFunction::class.java)
        for (function in functions) {
            val snakeCaseFunctionName = function.name ?: continue
            val expectedCamelCaseFunctionName = snakeCaseFunctionName.snakeToCamelCase()
            functionNameSnakeToCamelCase(function)
            val actualCamelCaseFunctionName = function.name ?: error("Can not get function name after case change!!!")
            assertTrue(
                "For the function with name $snakeCaseFunctionName " +
                        "the function functionNameSnakeToCamelCase should rename " +
                        "$snakeCaseFunctionName function into camel case, " +
                        "but we got $actualCamelCaseFunctionName which is not camel case!",
                expectedCamelCaseFunctionName == actualCamelCaseFunctionName
            )
        }
    }
}