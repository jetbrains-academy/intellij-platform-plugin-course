package org.jetbrains.academy.plugin.course.dev.edit

import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.kotlin.psi.KtNamedFunction

abstract class BaseEditFunctionNameTest : BasePlatformTestCase() {
    private fun getResourceFileContent(relativePath: String) =
        EditFunctionNameTest::class.java.getResourceAsStream(BASE_PATH + relativePath)?.bufferedReader()?.use { it.readText() } ?: ""
    private fun getFile(fileContent: String) =
        myFixture.configureByText("MyClass.kt", fileContent) ?: error("Internal course error!")

    fun doTest(relativePath: String) {
        val fileContent = getResourceFileContent(relativePath)
        val file = getFile(fileContent)
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

    private fun String.snakeToCamelCase(): String {
        return this.split('_').withIndex().joinToString("") { (index, it) ->
            if (index == 0) it.lowercase() else it.lowercase().replaceFirstChar(Char::titlecase)
        }
    }

    companion object {
        private const val BASE_PATH = "/backUpProjects/sort-methods-project/src/main/kotlin/org/jetbrains/academy/plugin/course/dev/project/examples/"
    }
}

class EditFunctionNameTest : BaseEditFunctionNameTest() {

    fun testSolution() {
        doTest("SnakeCaseFunctions.kt")
    }
}
