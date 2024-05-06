package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.academy.test.MyBaseTest
import org.jetbrains.kotlin.psi.*

private fun normalizeWhitespace(text: String): String {
    return text.replace("\\s+".toRegex(), " ").trim()
}

abstract class BaseReplaceFunctionCallsTest : MyBaseTest() {

    fun doTest(relativePath: String) {
        val fileContent = getResourceFileContent(relativePath)
        val file = getFile(fileContent)
        val authorFile = myFixture.configureByText("Author.kt", fileContent) ?: error("Internal course error!")

        val functions = PsiTreeUtil.findChildrenOfType(file, KtNamedFunction::class.java)
        val authorFunctions = PsiTreeUtil.findChildrenOfType(authorFile, KtNamedFunction::class.java)

        functions.zip(authorFunctions).forEachIndexed { index, (function, authorFunction) ->
            replaceFunctionCallArgumentsWithDataClass(project, file as KtFile, function, "DataClass$index")
            authorReplaceFunctionCallArgumentsWithDataClass(project, authorFile as KtFile, authorFunction, "DataClass$index")

            val functionCalls = PsiTreeUtil.collectElementsOfType(file, KtCallExpression::class.java)
                .filter { function.name?.let { it1 -> it.calleeExpression?.textMatches(it1) } == true }
            val authorFunctionCalls = PsiTreeUtil.collectElementsOfType(authorFile, KtCallExpression::class.java)
                .filter { authorFunction.name?.let { it1 -> it.calleeExpression?.textMatches(it1) } == true }


            functionCalls.zip(authorFunctionCalls).forEach { (functionCall, authorFunctionCall) ->
                val argumentList = functionCall.valueArgumentList
                val authorArgumentList = authorFunctionCall.valueArgumentList
                assertEquals("Argument lists do not match", normalizeWhitespace(authorArgumentList?.text ?: ""), normalizeWhitespace(argumentList?.text ?: ""))
            }
        }
    }
}

class ReplaceFunctionCallsTest : BaseReplaceFunctionCallsTest() {
    override val resourceClass: Class<*>
        get() = ReplaceFunctionCallsTest::class.java

    fun testSolution() {
        doTest("Main.kt")
    }
}
