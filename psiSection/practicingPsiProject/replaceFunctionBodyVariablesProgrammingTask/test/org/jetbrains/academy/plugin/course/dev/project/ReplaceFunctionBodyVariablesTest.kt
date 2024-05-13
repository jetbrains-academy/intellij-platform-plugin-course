package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.academy.test.MyBaseTest
import org.jetbrains.kotlin.psi.KtNamedFunction

private fun normalizeWhitespace(text: String): String {
    return text.replace("\\s+".toRegex(), " ").trim()
}
abstract class BaseReplaceFunctionBodyVariablesTest : MyBaseTest() {

    fun doTest(relativePath: String) {
        val fileContent = getResourceFileContent(relativePath)
        val file = getFile(fileContent)
        val authorFile = myFixture.configureByText("Author.kt", fileContent) ?: error("Internal course error!")

        val functions = PsiTreeUtil.findChildrenOfType(file, KtNamedFunction::class.java)
        val authorFunctions = PsiTreeUtil.findChildrenOfType(authorFile, KtNamedFunction::class.java)

        functions.zip(authorFunctions).forEach { (ktFunction, authorFunction) ->
            refactorFunctionBody(ktFunction)
            authorRefactorFunctionBody(authorFunction)

            val normalizedOriginalFunctionBody = normalizeWhitespace(ktFunction.bodyExpression?.text ?: "")
            val normalizedAuthorFunctionBody = normalizeWhitespace(authorFunction.bodyExpression?.text ?: "")

            assertEquals(normalizedOriginalFunctionBody, normalizedAuthorFunctionBody)
        }
    }
}

class ReplaceFunctionBodyVariablesTest : BaseReplaceFunctionBodyVariablesTest() {
    override val resourceClass: Class<*>
        get() = ReplaceFunctionBodyVariablesTest::class.java

    fun testSolution() {
        doTest("Main.kt")
    }
}
